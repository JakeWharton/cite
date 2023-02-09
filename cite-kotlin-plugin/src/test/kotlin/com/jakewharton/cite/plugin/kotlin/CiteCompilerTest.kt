package com.jakewharton.cite.plugin.kotlin

import com.tschuchort.compiletesting.SourceFile
import kotlin.test.Test
import strikt.assertions.contains

class CiteCompilerTest {
	@Test fun typeTopLevelFunctionFails() {
		val source = SourceFile.kotlin(
			"main.kt",
			"""
			import com.jakewharton.cite.__TYPE__
			fun noType() = __TYPE__
			""",
		)
		jvmCompile(source)
			.isCompilerFailure()
			.messages
			.contains("main.kt:2:16 __TYPE__ may only be used within a type")
	}

	@Test fun typeTopLevelPropertyInitializerFails() {
		val source = SourceFile.kotlin(
			"main.kt",
			"""
			import com.jakewharton.cite.__TYPE__
			val noType = __TYPE__
			""",
		)
		jvmCompile(source)
			.isCompilerFailure()
			.messages
			.contains("main.kt:2:14 __TYPE__ may only be used within a type")
	}

	@Test fun typeTopLevelPropertyGetterFails() {
		val source = SourceFile.kotlin(
			"main.kt",
			"""
			import com.jakewharton.cite.__TYPE__
			val noType get() = __TYPE__
			""",
		)
		jvmCompile(source)
			.isCompilerFailure()
			.messages
			.contains("main.kt:2:20 __TYPE__ may only be used within a type")
	}

	@Test fun typeTopLevelPropertySetterFails() {
		val source = SourceFile.kotlin(
			"main.kt",
			"""
			import com.jakewharton.cite.__TYPE__
			var noType: String = ""
				set(_) {
					field = __TYPE__
				}
			""",
		)
		jvmCompile(source)
			.isCompilerFailure()
			.messages
			.contains("main.kt:4:11 __TYPE__ may only be used within a type")
	}

	@Test fun memberTopLevelPropertyInitializerFails() {
		val source = SourceFile.kotlin(
			"main.kt",
			"""
			import com.jakewharton.cite.__MEMBER__
			val noMember = __MEMBER__
			""",
		)
		jvmCompile(source)
			.isCompilerFailure()
			.messages
			.contains("main.kt:2:16 __MEMBER__ may only be used within a member")
	}
}
