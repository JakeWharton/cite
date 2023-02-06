package com.example.cite

import kotlin.test.Test
import kotlin.test.assertEquals

class NestedClassTest {
	@Test fun test() {
		assertEquals("OuterType", OuterType.first)
		assertEquals("OuterType", OuterType.second)
		assertEquals("InnerType", OuterType.InnerType.inner)
	}
}
