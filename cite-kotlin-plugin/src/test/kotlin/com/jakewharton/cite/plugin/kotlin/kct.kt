package com.jakewharton.cite.plugin.kotlin

import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.KotlinCompilation.ExitCode
import com.tschuchort.compiletesting.KotlinCompilation.Result
import com.tschuchort.compiletesting.SourceFile
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import assertk.assertThat
import assertk.Assert
import assertk.assertions.prop
import assertk.fail

fun jvmCompile(vararg files: SourceFile): Assert<Result> {
	return KotlinCompilation()
		.apply {
			sources = files.toList()
			compilerPluginRegistrars = listOf(CiteCompilerPluginRegistrar())
			// https://github.com/ZacSweers/kotlin-compile-testing/pull/124
			commandLineProcessors = listOf(object : CommandLineProcessor {
				override val pluginId get() = ""
				override val pluginOptions get() = emptySet<AbstractCliOption>()
			})
			inheritClassPath = true
			useIR = true
		}
		.compile()
		.let(::assertThat)
}

fun Assert<Result>.isSuccess() = given {
	if (it.exitCode != ExitCode.OK) {
		fail("${it.exitCode} ${it.messages}")
	}
}

fun Assert<Result>.isCompilerFailure() = given {
	if (it.exitCode != ExitCode.COMPILATION_ERROR) {
		fail("${it.exitCode} ${it.messages}")
	}
}

val Assert<Result>.messages: Assert<String> get() = prop(Result::messages)
