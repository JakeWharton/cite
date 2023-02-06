package com.example.cite

import kotlin.test.Test
import kotlin.test.assertEquals

class ObjectTest {
	@Test fun initializer() {
		assertEquals("object.kt", AnObject.instanceInitializerFile)
		assertEquals("AnObject", AnObject.instanceInitializerType)
		assertEquals("<init>", AnObject.instanceInitializerMember)
		assertEquals(48, AnObject.instanceInitializerLine)
	}

	@Test fun `fun`() {
		assertEquals("object.kt", AnObject.funFile())
		assertEquals("AnObject", AnObject.funType())
		assertEquals("funMember", AnObject.funMember())
		assertEquals(12, AnObject.funLine())
	}

	@Test fun propertyInitializer() {
		assertEquals("object.kt", AnObject.propertyInitializerFile)
		assertEquals("AnObject", AnObject.propertyInitializerType)
		assertEquals(16, AnObject.propertyInitializerLine)
	}

	@Test fun propertyGetter() {
		assertEquals("object.kt", AnObject.propertyGetterFile)
		assertEquals("AnObject", AnObject.propertyGetterType)
		// TODO Should be just "propertyGetterMember".
		assertEquals("<get-propertyGetterMember>", AnObject.propertyGetterMember)
		assertEquals(21, AnObject.propertyGetterLine)
	}

	@Test fun propertySetter() {
		// Write triggers persisting cited values.
		AnObject.propertySetterFile = "dummy"
		AnObject.propertySetterType = "dummy"
		AnObject.propertySetterMember = "dummy"
		AnObject.propertySetterLine = -1

		assertEquals("object.kt", AnObject.propertySetterFile)
		assertEquals("AnObject", AnObject.propertySetterType)
		// TODO Should be just "propertySetterMember".
		assertEquals("<set-propertySetterMember>", AnObject.propertySetterMember)
		assertEquals(37, AnObject.propertySetterLine)
	}
}
