package com.jakewharton.cite.plugin.kotlin

import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.backend.common.lower.DeclarationIrBuilder
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity.ERROR
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.builders.declarations.buildFun
import org.jetbrains.kotlin.ir.builders.irBlockBody
import org.jetbrains.kotlin.ir.builders.irReturn
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent
import org.jetbrains.kotlin.ir.declarations.IrFile
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
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.name.SpecialNames

internal class CiteElementTransformer(
	private val messageCollector: MessageCollector,
	private val pluginContext: IrPluginContext,
) : IrElementTransformerVoidWithContext() {
	private val fileName = FqName("com.jakewharton.cite.<get-__FILE__>")
	private val typeName = FqName("com.jakewharton.cite.<get-__TYPE__>")
	private val memberName = FqName("com.jakewharton.cite.<get-__MEMBER__>")
	private val lineName = FqName("com.jakewharton.cite.<get-__LINE__>")

	private val function0 = pluginContext.referenceClass(ClassId(FqName("kotlin"), Name.identifier("Function0")))!!

	private var visitingFile: IrFile? = null
	private var visitingType = ArrayDeque<IrClass>()
	private var visitingMember = ArrayDeque<IrElement>()

	override fun visitFileNew(declaration: IrFile): IrFile {
		visitingFile = declaration
		val irFile = super.visitFileNew(declaration)
		visitingFile = null
		return irFile
	}

	override fun visitClassNew(declaration: IrClass): IrStatement {
		visitingType += declaration
		val irStatement = super.visitClassNew(declaration)
		visitingType.removeLast()
		return irStatement
	}

	override fun visitFunctionNew(declaration: IrFunction): IrStatement {
		val anonymous = declaration.name == SpecialNames.ANONYMOUS
		if (!anonymous) {
			visitingMember += declaration
		}
		val irStatement = super.visitFunctionNew(declaration)
		if (!anonymous) {
			visitingMember.removeLast()
		}
		return irStatement
	}

	override fun visitAnonymousInitializerNew(declaration: IrAnonymousInitializer): IrStatement {
		visitingMember += declaration
		val irStatement = super.visitAnonymousInitializerNew(declaration)
		visitingMember.removeLast()
		return irStatement
	}

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
					parent = visitingMember.last() as IrDeclarationParent
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

	private fun maybeReplaceCitation(source: IrExpression, owner: IrSimpleFunction): IrConst<*>? {
		when (owner.kotlinFqName) {
			fileName -> {
				val visitingFile = visitingFile
				if (visitingFile != null) {
					val name = visitingFile.fileEntry.name.substringAfterLast(File.separator)
					return source.swapConstString(name)
				}
				source.reportError("No file detected! Report bug at https://github.com/JakeWharton/cite/issues/new")
			}
			typeName -> {
				val visitingType = visitingType.lastOrNull()
				if (visitingType != null) {
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
				val visitingMember = visitingMember.lastOrNull()
				if (visitingMember != null) {
					val name = when (visitingMember) {
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
				val visitingFile = visitingFile
				if (visitingFile != null) {
					val rangeInfo = visitingFile.fileEntry.getSourceRangeInfo(
						source.startOffset,
						source.endOffset,
					)
					val line = rangeInfo.startLineNumber + 1 // Humans are one-based.
					return source.swapConstInt(line)
				}
				source.reportError("No line number detected! Report bug at https://github.com/JakeWharton/cite/issues/new")
			}
		}

		return null
	}

	private fun IrExpression.swapConstString(value: String): IrConst<String> {
		return IrConstImpl.string(startOffset, endOffset, pluginContext.irBuiltIns.stringType, value)
	}

	private fun IrExpression.swapConstInt(value: Int): IrConst<Int> {
		return IrConstImpl.int(startOffset, endOffset, pluginContext.irBuiltIns.intType, value)
	}

	private fun IrExpression.reportError(message: String) {
		val location = visitingFile?.let { visitingFile ->
			val rangeInfo = visitingFile.fileEntry.getSourceRangeInfo(
				startOffset,
				endOffset,
			)
			CompilerMessageLocation.create(
				path = visitingFile.fileEntry.name,
				line = rangeInfo.startLineNumber + 1, // Location is one-based.
				column = rangeInfo.startColumnNumber + 1, // Location is one-based.
				lineContent = null,
			)
		}
		messageCollector.report(ERROR, message, location)
	}
}
