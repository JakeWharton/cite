package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.jakewharton.cite.__MODULE__
import kotlin.test.Test

class ModuleTest {
	@Test fun main() {
		assertThat(mainModuleName).isEqualTo("cite:cite-tests")
	}

	@Test fun test() {
		assertThat(__MODULE__).isEqualTo("cite:cite-tests_test")
	}
}
