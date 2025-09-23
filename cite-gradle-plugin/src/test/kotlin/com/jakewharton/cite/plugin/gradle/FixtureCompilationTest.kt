package com.jakewharton.cite.plugin.gradle

import assertk.assertThat
import assertk.assertions.contains
import java.io.File
import org.gradle.testkit.runner.GradleRunner
import org.junit.Test

class FixtureCompilationTest {
	@Test fun noKotlinFails() {
		val result = createRunner("no-kotlin").buildAndFail()
		assertThat(result.output).contains("No suitable Kotlin configuration was found")
	}

	@Test fun androidApplication() {
		createRunner("android-application", "assembleAndroidTest").build()
	}

	@Test fun androidDynamicFeature() {
		createRunner("android-dynamic-feature", "assembleAndroidTest").build()
	}

	@Test fun androidLibrary() {
		createRunner("android-library", "assembleAndroidTest").build()
	}

	@Test fun androidLibraryNoTests() {
		createRunner("android-library-no-tests", "assembleAndroidTest").build()
	}

	@Test fun androidTest() {
		createRunner("android-test", "assembleAndroidTest").build()
	}

	@Test fun jvm() {
		createRunner("jvm").build()
	}

	private fun createRunner(fixtureName: String, vararg otherTasks: String): GradleRunner {
		val fixtureDir = File("src/test/fixtures", fixtureName)
		val gradleRoot = File(fixtureDir, "gradle").also { it.mkdir() }
		File("../gradle/wrapper").copyRecursively(File(gradleRoot, "wrapper"), true)
		return GradleRunner.create()
			.withProjectDir(fixtureDir)
			.withDebug(true) // Run in-process
			.withArguments(
				"clean",
				"build",
				*otherTasks,
				"--stacktrace",
				"--continue",
				"-PciteVersion=$CiteVersion",
			)
			.forwardOutput()
	}
}
