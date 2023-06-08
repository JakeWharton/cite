package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class NestedClassTest {
	@Test fun test() {
		assertThat(OuterType.first).isEqualTo("OuterType")
		assertThat(OuterType.second).isEqualTo("OuterType")
		assertThat(OuterType.InnerType.inner).isEqualTo("InnerType")
	}
}
