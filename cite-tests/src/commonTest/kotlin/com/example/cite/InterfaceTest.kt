package com.example.cite

import kotlin.test.Test
import kotlin.test.assertEquals

class InterfaceTest {
	@Test fun `fun`() {
		assertEquals("interface.kt", AnInterface.Instance.funFile())
		assertEquals("AnInterface", AnInterface.Instance.funType())
		assertEquals("funMember", AnInterface.Instance.funMember())
		assertEquals(12, AnInterface.Instance.funLine())
	}

	@Test fun propertyGetter() {
		assertEquals("interface.kt", AnInterface.Instance.propertyGetterFile)
		assertEquals("AnInterface", AnInterface.Instance.propertyGetterType)
		// TODO Should be just "propertyGetterMember".
		assertEquals("<get-propertyGetterMember>", AnInterface.Instance.propertyGetterMember)
		assertEquals(17, AnInterface.Instance.propertyGetterLine)
	}

	@Test fun propertySetter() {
		// Write triggers persisting cited values to companion properties.
		AnInterface.Instance.propertySetterFile = "dummy"
		AnInterface.Instance.propertySetterType = "dummy"
		AnInterface.Instance.propertySetterMember = "dummy"
		AnInterface.Instance.propertySetterLine = -1

		assertEquals("interface.kt", AnInterface.setterFileValue)
		assertEquals("AnInterface", AnInterface.setterTypeValue)
		// TODO Should be just "propertySetterMember".
		assertEquals("<set-propertySetterMember>", AnInterface.setterMemberValue)
		assertEquals(37, AnInterface.setterLineValue)
	}
}
