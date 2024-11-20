package com.jakewharton.cite.plugin.gradle

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.SourceSet.MAIN_SOURCE_SET_NAME
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet.Companion.COMMON_MAIN_SOURCE_SET_NAME
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

public class CitePlugin : KotlinCompilerPluginSupportPlugin {
	override fun apply(target: Project) {
		super.apply(target)

		var applied = false
		target.plugins.withId("org.jetbrains.kotlin.multiplatform") {
			applied = true
			val kotlin = target.extensions.getByType(KotlinMultiplatformExtension::class.java)
			kotlin.sourceSets.getByName(COMMON_MAIN_SOURCE_SET_NAME).dependencies {
				implementation(target.citeRuntimeDependency())
			}
		}
		target.plugins.withId("org.jetbrains.kotlin.jvm") {
			applied = true
			val kotlin = target.extensions.getByType(KotlinJvmProjectExtension::class.java)
			kotlin.sourceSets.getByName(MAIN_SOURCE_SET_NAME).dependencies {
				implementation(target.citeRuntimeDependency())
			}
		}
		target.plugins.withId("org.jetbrains.kotlin.android") {
			applied = true
			val kotlin = target.extensions.getByType(KotlinAndroidProjectExtension::class.java)
			kotlin.sourceSets.getByName(MAIN_SOURCE_SET_NAME).dependencies {
				implementation(target.citeRuntimeDependency())
			}
		}
		target.afterEvaluate {
			check(applied) {
				"The Cite plugin requires either the Kotlin Multiplatform, JVM, or Android plugin"
			}
		}
	}

	private fun Project.citeRuntimeDependency(): Any {
		// Indicates when the plugin is applied inside the Cite repo to Cite's own modules. This
		// changes dependencies from being external Maven coordinates to internal project references.
		val isInternalBuild = properties["com.jakewharton.cite.internal"].toString() == "true"

		return if (isInternalBuild) {
			project(":cite-runtime")
		} else {
			"com.jakewharton.cite:cite-runtime:$CiteVersion"
		}
	}

	override fun isApplicable(kotlinCompilation: KotlinCompilation<*>): Boolean = true

	override fun getCompilerPluginId(): String = "com.jakewharton.cite"

	override fun getPluginArtifact(): SubpluginArtifact {
		return SubpluginArtifact(
			groupId = "com.jakewharton.cite",
			artifactId = "cite-kotlin-plugin",
			version = CiteVersion,
		)
	}

	override fun applyToCompilation(kotlinCompilation: KotlinCompilation<*>): Provider<List<SubpluginOption>> {
		return kotlinCompilation.target.project.provider { emptyList() }
	}
}
