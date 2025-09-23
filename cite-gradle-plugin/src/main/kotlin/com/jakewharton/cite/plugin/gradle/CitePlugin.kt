package com.jakewharton.cite.plugin.gradle

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSet.MAIN_SOURCE_SET_NAME
import org.gradle.api.tasks.SourceSet.TEST_SOURCE_SET_NAME
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinBaseApiPlugin
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet.Companion.COMMON_MAIN_SOURCE_SET_NAME

public class CitePlugin : Plugin<Project> {
	override fun apply(target: Project) {
		var applied = false
		target.afterEvaluate {
			check(applied) { "No suitable Kotlin configuration was found" }
		}

		target.pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
			applied = true
			target.pluginManager.apply(CiteKotlinPlugin::class.java)

			val kotlin = target.extensions.getByType(KotlinMultiplatformExtension::class.java)
			kotlin.sourceSets.getByName(COMMON_MAIN_SOURCE_SET_NAME).dependencies {
				implementation(target.citeApiDependency())
			}
		}
		target.pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
			applied = true
			target.pluginManager.apply(CiteKotlinPlugin::class.java)

			val kotlin = target.extensions.getByType(KotlinJvmProjectExtension::class.java)
			kotlin.sourceSets.getByName(MAIN_SOURCE_SET_NAME).dependencies {
				compileOnly(target.citeApiDependency())
			}
			kotlin.sourceSets.getByName(TEST_SOURCE_SET_NAME).dependencies {
				compileOnly(target.citeApiDependency())
			}
		}
		target.pluginManager.withPlugin("org.jetbrains.kotlin.android") {
			applied = true
			target.pluginManager.apply(CiteKotlinPlugin::class.java)

			val kotlin = target.extensions.getByType(KotlinAndroidProjectExtension::class.java)
			kotlin.sourceSets.getByName(MAIN_SOURCE_SET_NAME).dependencies {
				compileOnly(target.citeApiDependency())
			}
			kotlin.sourceSets.getByName(TEST_SOURCE_SET_NAME).dependencies {
				compileOnly(target.citeApiDependency())
			}
			kotlin.sourceSets.getByName("androidTest").dependencies {
				compileOnly(target.citeApiDependency())
			}
		}
		target.pluginManager.withPlugin("com.android.base") {
			if (target.plugins.hasPlugin(KotlinBaseApiPlugin::class.java)) {
				applied = true
				target.pluginManager.apply(CiteKotlinPlugin::class.java)

				val android = target.extensions.getByType(CommonExtension::class.java)
				android.sourceSets.configureEach { sourceSet ->
					target.dependencies.add(sourceSet.compileOnlyConfigurationName, target.citeApiDependency())
				}
			}
		}
	}

	private fun Project.citeApiDependency(): Any {
		// Indicates when the plugin is applied inside the Cite repo to Cite's own modules. This
		// changes dependencies from being external Maven coordinates to internal project references.
		val isInternalBuild = providers.gradleProperty("com.jakewharton.cite.internal")
			.getOrElse("false")
			.toBoolean()

		return if (isInternalBuild) {
			project(":cite-api")
		} else {
			"com.jakewharton.cite:cite-api:$CiteVersion"
		}
	}
}
