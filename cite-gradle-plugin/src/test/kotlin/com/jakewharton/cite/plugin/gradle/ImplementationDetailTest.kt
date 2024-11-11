package com.jakewharton.cite.plugin.gradle

import assertk.all
import assertk.assertThat
import assertk.assertions.contains
import java.io.File
import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Ignore
import org.junit.Test

class ImplementationDetailTest {
	private companion object {
		val fixtureDir = File("src/test/fixtures/implementation-details")

		@BeforeClass
		@JvmStatic fun first() {
			val gradleRoot = File(fixtureDir, "gradle").also { it.mkdir() }
			File("../gradle").copyRecursively(gradleRoot, true)
		}
	}

	private lateinit var result: BuildResult

	@Before fun before() {
		result = GradleRunner.create()
			.withProjectDir(fixtureDir)
			.withDebug(true) // Run in-process.
			.withArguments(
				"clean",
				"assemble",
				"-PciteVersion=$CiteVersion",
			)
			.forwardOutput()
			.build()
	}

	@Test fun js() {
		val jsFile = fixtureDir.resolve("build/compileSync/js/main/productionExecutable/kotlin/implementation-details.js")
		val javaScript = jsFile.readText()
		assertThat(javaScript)
			.contains("""
				|  protoOf(Greeter).h = function () {
				|    println('Hello: main.kt, Greeter, sayHi, 17');
				|  };
				""".trimMargin())
	}

	@Test fun jvm() {
		val classFile = fixtureDir.resolve("build/classes/kotlin/jvm/main/com/example/cite/Greeter.class")
		val bytecode = classFile.readBytes()
		val bytecodeText = bytecodeToText(bytecode)
		assertThat(bytecodeText)
			.contains("""
				|    LINENUMBER 17 L0
				|    LDC "Hello: main.kt, Greeter, sayHi, 17"
				|    GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
				|    SWAP
				|    INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/Object;)V
				""".trimMargin())
	}

	@Test fun native() {
		val bitcodeFile = fixtureDir.resolve("build/dump/out.Codegen.ll")
		val bitcode = bitcodeFile.readText()
		assertThat(bitcode).all {
			contains("call void @\"kfun:kotlin.io#println(kotlin.Any?){}\"(%struct.ObjHeader* bitcast ({ %struct.ArrayHeader, [34 x i16] }* @814 to %struct.ObjHeader*))")
			contains("@814 = internal unnamed_addr constant { %struct.ArrayHeader, [34 x i16] } { %struct.ArrayHeader { %struct.TypeInfo* bitcast (i8* getelementptr (i8, i8* bitcast ({ %struct.TypeInfo, [3 x i8*] }* @\"kclass:kotlin.String\" to i8*), i32 1) to %struct.TypeInfo*), i32 34 }, [34 x i16] [i16 72, i16 101, i16 108, i16 108, i16 111, i16 58, i16 32, i16 109, i16 97, i16 105, i16 110, i16 46, i16 107, i16 116, i16 44, i16 32, i16 71, i16 114, i16 101, i16 101, i16 116, i16 101, i16 114, i16 44, i16 32, i16 115, i16 97, i16 121, i16 72, i16 105, i16 44, i16 32, i16 49, i16 55] }")
		}
	}

	@Ignore
	@Test fun wasm() {
		TODO()
	}
}
