package com.jakewharton.cite.plugin.kotlin

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration

@OptIn(ExperimentalCompilerApi::class)
@AutoService(CompilerPluginRegistrar::class)
public class CiteCompilerPluginRegistrar : CompilerPluginRegistrar() {
	override val supportsK2: Boolean get() = true

	override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
		val messageCollector = configuration.get(
			CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY,
			MessageCollector.NONE
		)
		IrGenerationExtension.registerExtension(CiteIrGenerationExtension(messageCollector))
	}
}
