package com.example.cite

import NoPackage
import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class FqTypeTest {
	@Test fun noPackage() {
		assertThat(NoPackage().fqType()).isEqualTo("NoPackage")
		assertThat(NoPackage.fqType()).isEqualTo("NoPackage")
	}
}
