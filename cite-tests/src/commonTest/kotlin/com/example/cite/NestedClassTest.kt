package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class NestedClassTest {
	@Test fun test() {
		assertThat(OuterType.outer).isEqualTo("OuterType")
		assertThat(OuterType.outerFq).isEqualTo("com.example.cite.OuterType")
		assertThat(OuterType.InnerType.inner).isEqualTo("InnerType")
		assertThat(OuterType.InnerType.innerFq).isEqualTo("com.example.cite.OuterType.InnerType")
	}
}
