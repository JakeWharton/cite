package com.jakewharton.cite.plugin.kotlin

import org.jetbrains.kotlin.backend.common.IrElementTransformerVoidWithContext
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageLocation
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity.ERROR
import org.jetbrains.kotlin.cli.common.messages.MessageCollector
import org.jetbrains.kotlin.ir.IrFileEntry
import org.jetbrains.kotlin.ir.IrStatement
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrFile
import org.jetbrains.kotlin.ir.declarations.IrFunction
import org.jetbrains.kotlin.ir.expressions.IrCall
import org.jetbrains.kotlin.ir.expressions.IrExpression
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.types.getClass
import org.jetbrains.kotlin.ir.util.isEnumEntry
import org.jetbrains.kotlin.ir.util.isPropertyAccessor
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.konan.file.File
import org.jetbrains.kotlin.name.FqName

internal class CiteElementTransformer(
	private val messageCollector: MessageCollector,
) : IrElementTransformerVoidWithContext() {
	private val fileName = FqName("com.jakewharton.cite.<get-__FILE__>")
	private val typeName = FqName("com.jakewharton.cite.<get-__TYPE__>")
	private val memberName = FqName("com.jakewharton.cite.<get-__MEMBER__>")
	private val lineName = FqName("com.jakewharton.cite.<get-__LINE__>")

	private var visitingFile: IrFileEntry? = null
	private var visitingType = ArrayDeque<String>()
	private var visitingMember = ArrayDeque<String>()

	override fun visitFileNew(declaration: IrFile): IrFile {
		visitingFile = declaration.fileEntry
		val irFile = super.visitFileNew(declaration)
		visitingFile = null
		return irFile
	}

	override fun visitClassNew(declaration: IrClass): IrStatement {
		visitingType += if (declaration.isEnumEntry) {
			declaration.superTypes.first().getClass()!!.name.asString()
		} else {
			declaration.name.asString()
		}
		val irStatement = super.visitClassNew(declaration)
		visitingType.removeLast()
		return irStatement
	}

	override fun visitFunctionNew(declaration: IrFunction): IrStatement {
		visitingMember += declaration.name.asString()
		val irStatement = super.visitFunctionNew(declaration)
		visitingMember.removeLast()
		return irStatement
	}

	override fun visitAnonymousInitializerNew(declaration: IrAnonymousInitializer): IrStatement {
		visitingMember += "<init>"
		val irStatement = super.visitAnonymousInitializerNew(declaration)
		visitingMember.removeLast()
		return irStatement
	}

	override fun visitCall(expression: IrCall): IrExpression {
		val owner = expression.symbol.owner
		if (owner.isPropertyAccessor) {
			when (owner.kotlinFqName) {
				fileName -> {
					val visitingFile = visitingFile
					if (visitingFile != null) {
						return expression.swapConstString(visitingFile.name.substringAfterLast(File.separator))
					} else {
						expression.reportError("No file detected! Report bug at https://github.com/JakeWharton/cite/issues/new")
					}
				}
				typeName -> {
					val visitingType = visitingType.lastOrNull()
					if (visitingType != null) {
						return expression.swapConstString(visitingType)
					} else {
						expression.reportError("__TYPE__ may only be used within a type")
					}
				}
				memberName -> {
					val visitingMember = visitingMember.lastOrNull()
					if (visitingMember != null) {
						return expression.swapConstString(visitingMember)
					} else {
						expression.reportError("No member detected! Report bug at https://github.com/JakeWharton/cite/issues/new")
					}
				}
				lineName -> {
					val visitingFile = visitingFile
					if (visitingFile != null) {
						val rangeInfo = visitingFile.getSourceRangeInfo(
							expression.startOffset,
							expression.endOffset,
						)
						val line = rangeInfo.startLineNumber + 1 // Humans are one-based.
						return expression.swapConstInt(line)
					} else {
						expression.reportError("No line number detected! Report bug at https://github.com/JakeWharton/cite/issues/new")
					}
				}
			}
		}

		return super.visitCall(expression) as IrCall
	}

	private fun IrExpression.swapConstString(value: String): IrExpression {
		return IrConstImpl.string(startOffset, endOffset, type, value)
	}

	private fun IrExpression.swapConstInt(value: Int): IrExpression {
		return IrConstImpl.int(startOffset, endOffset, type, value)
	}

	private fun IrExpression.reportError(message: String) {
		val location = visitingFile?.let { visitingFile ->
			val rangeInfo = visitingFile.getSourceRangeInfo(
				startOffset,
				endOffset,
			)
			CompilerMessageLocation.create(
				path = visitingFile.name,
				line = rangeInfo.startLineNumber + 1, // Location is one-based.
				column = rangeInfo.startColumnNumber + 1, // Location is one-based.
				lineContent = null,
			)
		}
		messageCollector.report(ERROR, message, location)
	}
}
