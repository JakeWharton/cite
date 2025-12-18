package com.jakewharton.cite.plugin.gradle

import assertk.assertThat
import assertk.assertions.contains
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import java.io.File
import org.gradle.testkit.runner.GradleRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class FixtureCompilationTest(
	@param:TestParameter(LATEST_GRADLE_VERSION, MINIMUM_GRADLE_VERSION)
	private val gradleVersion: String,
) {
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
			.apply {
				if (gradleVersion != LATEST_GRADLE_VERSION) {
					withGradleVersion(gradleVersion)
				}
			}
			.withProjectDir(fixtureDir)
			.withDebug(true) // Run in-process
			.withArguments(
				"clean",
				"build",
				*otherTasks,
				"--stacktrace",
				"--continue",
				"--no-build-cache",
				"--no-configuration-cache", // KGP's problem.
				VERSION_PROPERTY,
				VALIDATE_KOTLIN_METADATA,
			)
			.forwardOutput()
	}
}

private const val VERSION_PROPERTY = "-PciteVersion=$CiteVersion"
private const val LATEST_GRADLE_VERSION = "latest"
private const val VALIDATE_KOTLIN_METADATA = "-Porg.gradle.kotlin.dsl.skipMetadataVersionCheck=false"
