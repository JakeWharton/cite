package com.jakewharton.cite.plugin.kotlin

import assertk.Assert
import assertk.assertThat
import assertk.assertions.prop
import assertk.fail
import com.tschuchort.compiletesting.JvmCompilationResult
import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.KotlinCompilation.ExitCode
import com.tschuchort.compiletesting.SourceFile

fun jvmCompile(vararg files: SourceFile): Assert<JvmCompilationResult> {
	return KotlinCompilation()
		.apply {
			sources = files.toList()
			compilerPluginRegistrars = listOf(CiteCompilerPluginRegistrar())
			inheritClassPath = true
		}
		.compile()
		.let(::assertThat)
}

fun Assert<JvmCompilationResult>.isCompilerFailure() = given {
	if (it.exitCode != ExitCode.COMPILATION_ERROR) {
		fail("${it.exitCode} ${it.messages}")
	}
}

val Assert<JvmCompilationResult>.messages: Assert<String> get() = prop(JvmCompilationResult::messages)
