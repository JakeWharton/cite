package com.jakewharton.cite.plugin.kotlin

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.config.CommonConfigurationKeys
import org.jetbrains.kotlin.config.CompilerConfiguration

public class CiteCompilerPluginRegistrar : CompilerPluginRegistrar() {
	override val supportsK2: Boolean get() = true
	override val pluginId: String get() = "com.jakewharton.cite"

	override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
		val messageCollector = configuration.get(
			CommonConfigurationKeys.MESSAGE_COLLECTOR_KEY,
			MessageCollector.NONE,
		)
		IrGenerationExtension.registerExtension(CiteIrGenerationExtension(messageCollector))
	}
}
