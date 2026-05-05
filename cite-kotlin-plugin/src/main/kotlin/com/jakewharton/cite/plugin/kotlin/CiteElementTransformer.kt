package com.jakewharton.cite.plugin.kotlin

import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity.ERROR
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.ir.builders.declarations.buildFun
import org.jetbrains.kotlin.ir.builders.irBlockBody
import org.jetbrains.kotlin.ir.builders.irReturn
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrConst
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.IrPropertyReference
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrFunctionExpressionImpl
import org.jetbrains.kotlin.ir.types.getClass
import org.jetbrains.kotlin.ir.types.typeWith
import org.jetbrains.kotlin.ir.util.SYNTHETIC_OFFSET
import org.jetbrains.kotlin.ir.util.isEnumEntry
import org.jetbrains.kotlin.ir.util.isPropertyAccessor
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.konan.file.File
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.SpecialNames

internal class CiteElementTransformer(
	private val messageCollector: MessageCollector,
	private val pluginContext: IrPluginContext,
) : IrElementTransformerVoidWithContext() {
	private val fileName = FqName("com.jakewharton.cite.<get-__FILE__>")
	private val typeName = FqName("com.jakewharton.cite.<get-__TYPE__>")
	private val memberName = FqName("com.jakewharton.cite.<get-__MEMBER__>")
	private val lineName = FqName("com.jakewharton.cite.<get-__LINE__>")

	private val function0 = pluginContext.irBuiltIns.functionN(0)

	override fun visitPropertyReference(expression: IrPropertyReference): IrExpression {
		expression.getter?.let { getter ->
			val owner = getter.owner
			maybeReplaceCitation(expression, owner)?.let { replacement ->
				val function = pluginContext.irFactory.buildFun {
					startOffset = SYNTHETIC_OFFSET
					endOffset = SYNTHETIC_OFFSET
					returnType = replacement.type
					origin = IrDeclarationOrigin.LOCAL_FUNCTION_FOR_LAMBDA
					name = SpecialNames.ANONYMOUS
					visibility = DescriptorVisibilities.LOCAL
				}.apply {
					parent = currentDeclarationParent!!
					body = DeclarationIrBuilder(pluginContext, symbol).irBlockBody {
						+irReturn(replacement)
					}
				}
				return IrFunctionExpressionImpl(
					startOffset = expression.startOffset,
					endOffset = expression.endOffset,
					type = function0.typeWith(listOf(replacement.type)),
					origin = IrStatementOrigin.LAMBDA,
					function = function
				)
			}
		}

		return super.visitPropertyReference(expression)
	}

	override fun visitCall(expression: IrCall): IrExpression {
		val owner = expression.symbol.owner
		if (owner.isPropertyAccessor) {
			maybeReplaceCitation(expression, owner)?.let { replacement ->
				return replacement
			}
		}

		return super.visitCall(expression) as IrCall
	}

	private fun maybeReplaceCitation(source: IrExpression, owner: IrSimpleFunction): IrConst? {
		when (owner.kotlinFqName) {
			fileName -> {
				val name = currentFile.fileEntry.name.substringAfterLast(File.separator)
				return source.swapConstString(name)
			}
			typeName -> {
				currentClass?.let {
					val visitingType = it.irElement as IrClass
					val name = if (visitingType.isEnumEntry) {
						visitingType.superTypes.first().getClass()!!.name.asString()
					} else {
						visitingType.name.asString()
					}
					return source.swapConstString(name)
				}
				source.reportError("__TYPE__ may only be used within a type")
			}
			memberName -> {
				val currentMember = allScopes.lastOrNull {
					(it.irElement is IrFunction && (it.irElement as IrFunction).name != SpecialNames.ANONYMOUS) ||
						it.irElement is IrAnonymousInitializer
				}
				currentMember?.let {
					val name = when (val visitingMember = it.irElement) {
						is IrFunction -> {
							if (visitingMember.isPropertyAccessor) {
								(visitingMember as IrSimpleFunction).correspondingPropertySymbol!!.owner.name.asString()
							} else {
								visitingMember.name.asString()
							}
						}
						is IrAnonymousInitializer -> "<init>"
						else -> throw RuntimeException("Unknown member $visitingMember")
					}
					return source.swapConstString(name)
				}
				source.reportError("__MEMBER__ may only be used within a member")
			}
			lineName -> {
				val rangeInfo = currentFile.fileEntry.getSourceRangeInfo(
					source.startOffset,
					source.endOffset,
				)
				val line = rangeInfo.startLineNumber + 1 // Humans are one-based.
				return source.swapConstInt(line)
			}
		}

		return null
	}

	private fun IrExpression.swapConstString(value: String): IrConst {
		return IrConstImpl.string(startOffset, endOffset, pluginContext.irBuiltIns.stringType, value)
	}

	private fun IrExpression.swapConstInt(value: Int): IrConst {
		return IrConstImpl.int(startOffset, endOffset, pluginContext.irBuiltIns.intType, value)
	}

	private fun IrExpression.reportError(message: String) {
		val rangeInfo = currentFile.fileEntry.getSourceRangeInfo(
			startOffset,
			endOffset,
		)
		val location = CompilerMessageLocation.create(
			path = currentFile.fileEntry.name,
			line = rangeInfo.startLineNumber + 1, // Location is one-based.
			column = rangeInfo.startColumnNumber + 1, // Location is one-based.
			lineContent = null,
		)
		messageCollector.report(ERROR, message, location)
	}
}
