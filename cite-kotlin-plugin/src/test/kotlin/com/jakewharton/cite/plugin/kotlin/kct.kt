package com.jakewharton.cite.plugin.kotlin

import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.KotlinCompilation.ExitCode
import com.tschuchort.compiletesting.KotlinCompilation.Result
import com.tschuchort.compiletesting.SourceFile
import org.jetbrains.kotlin.compiler.plugin.AbstractCliOption
import org.jetbrains.kotlin.compiler.plugin.CommandLineProcessor
import strikt.api.Assertion
import strikt.api.expectThat

fun jvmCompile(vararg files: SourceFile): Assertion.Builder<Result> {
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
		.let(::expectThat)
}

fun Assertion.Builder<Result>.isSuccess() = apply {
	assert("Compilation succeeded") {
		when (it.exitCode) {
			ExitCode.OK -> pass()
			else -> fail("${it.exitCode} ${it.messages}")
		}
	}
}

fun Assertion.Builder<Result>.isCompilerFailure() = apply {
	assert("Compilation failed") {
		when (it.exitCode) {
			ExitCode.COMPILATION_ERROR -> pass()
			else -> fail("${it.exitCode} ${it.messages}")
		}
	}
}

val Assertion.Builder<Result>.messages: Assertion.Builder<String> get() = get { messages }
