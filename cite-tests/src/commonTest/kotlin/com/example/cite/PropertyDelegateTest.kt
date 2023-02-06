package com.example.cite

import kotlin.test.Test
import kotlin.test.assertEquals

class PropertyDelegateTest {
	@Test fun classDelegates() {
		assertEquals("propertyDelegates.kt", PropertyDelegates.delegateFile)
		assertEquals("PropertyDelegates", PropertyDelegates.delegateType)
		assertEquals(11, PropertyDelegates.delegateLine)
	}

	@Test fun localDelegates() {
		val locals = PropertyDelegates.locals()
		assertEquals("propertyDelegates.kt", locals.delegateFile)
		assertEquals("PropertyDelegates", locals.delegateType)
		assertEquals("locals", locals.delegateMember)
		assertEquals(17, locals.delegateLine)
	}
}
