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
			contains("call void @\"kfun:kotlin.io#println(kotlin.Any?){}\"(ptr @760)")
			contains("@760 = internal unnamed_addr constant { ptr, i32, i32, i16, [68 x i8] } { ptr getelementptr inbounds (i8, ptr @\"kclass:kotlin.String\", i32 1), i32 35, i32 -1646021194, i16 0, [68 x i8] c\"H\\00e\\00l\\00l\\00o\\00:\\00 \\00m\\00a\\00i\\00n\\00.\\00k\\00t\\00,\\00 \\00G\\00r\\00e\\00e\\00t\\00e\\00r\\00,\\00 \\00s\\00a\\00y\\00H\\00i\\00,\\00 \\001\\007\\00\" }")
		}
	}

	@Ignore
	@Test fun wasm() {
		TODO()
	}
}
