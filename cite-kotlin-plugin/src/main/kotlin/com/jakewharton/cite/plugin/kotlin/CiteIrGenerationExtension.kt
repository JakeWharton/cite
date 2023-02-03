package com.jakewharton.cite.plugin.kotlin

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment

internal class CiteIrGenerationExtension(
	private val messageCollector: MessageCollector,
) : IrGenerationExtension {
	override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
		moduleFragment.transform(CiteElementTransformer(messageCollector), null)
	}
}
