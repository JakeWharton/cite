package com.example.cite

import kotlin.test.Test
import kotlin.test.assertEquals

class LocalClassTest {
	private val accessor = funWithClass()

	@Test fun `fun`() {
		assertEquals("localClass.kt", accessor.funFile())
		assertEquals("LocalClass", accessor.funType())
		assertEquals("funMember", accessor.funMember())
		assertEquals(13, accessor.funLine())
	}

	@Test fun propertyInitializer() {
		assertEquals("localClass.kt", accessor.propertyInitializerFile)
		assertEquals("LocalClass", accessor.propertyInitializerType)
		// TODO assertEquals("funWithClass", accessor.propertyInitializerMember)
		assertEquals(18, accessor.propertyInitializerLine)
	}

	@Test fun propertyGetter() {
		assertEquals("localClass.kt", accessor.propertyGetterFile)
		assertEquals("LocalClass", accessor.propertyGetterType)
		// TODO Should be just "propertyGetterMember".
		assertEquals("<get-propertyGetterMember>", accessor.propertyGetterMember)
		assertEquals(23, accessor.propertyGetterLine)
	}

	@Test fun propertySetter() {
		// Write triggers persisting cited values.
		accessor.propertySetterFile = "dummy"
		accessor.propertySetterType = "dummy"
		accessor.propertySetterMember = "dummy"
		accessor.propertySetterLine = -1

		assertEquals("localClass.kt", accessor.propertySetterFile)
		assertEquals("LocalClass", accessor.propertySetterType)
		// TODO Should be just "propertySetterMember".
		assertEquals("<set-propertySetterMember>", accessor.propertySetterMember)
		assertEquals(39, accessor.propertySetterLine)
	}
}
