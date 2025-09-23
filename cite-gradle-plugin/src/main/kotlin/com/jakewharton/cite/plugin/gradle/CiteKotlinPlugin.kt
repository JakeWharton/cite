package com.jakewharton.cite.plugin.gradle

import org.gradle.api.provider.Provider
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilation
import org.jetbrains.kotlin.gradle.plugin.KotlinCompilerPluginSupportPlugin
import org.jetbrains.kotlin.gradle.plugin.SubpluginArtifact
import org.jetbrains.kotlin.gradle.plugin.SubpluginOption

/**
 * Configures the Cite Kotlin compiler plugin.
 * Added by [CitePlugin] once Kotlin is detected.
 */
internal class CiteKotlinPlugin : KotlinCompilerPluginSupportPlugin {
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
