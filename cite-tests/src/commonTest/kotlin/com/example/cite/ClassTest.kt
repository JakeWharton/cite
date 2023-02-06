package com.example.cite

import kotlin.test.Test
import kotlin.test.assertEquals

class ClassTest {
	private val instance = AClass()

	@Test fun initializer() {
		assertEquals("class.kt", instance.instanceInitializerFile)
		assertEquals("AClass", instance.instanceInitializerType)
		assertEquals("<init>", instance.instanceInitializerMember)
		assertEquals(48, instance.instanceInitializerLine)
	}

	@Test fun `fun`() {
		assertEquals("class.kt", instance.funFile())
		assertEquals("AClass", instance.funType())
		assertEquals("funMember", instance.funMember())
		assertEquals(12, instance.funLine())
	}

	@Test fun propertyInitializer() {
		assertEquals("class.kt", instance.propertyInitializerFile)
		assertEquals("AClass", instance.propertyInitializerType)
		assertEquals(16, instance.propertyInitializerLine)
	}

	@Test fun propertyGetter() {
		assertEquals("class.kt", instance.propertyGetterFile)
		assertEquals("AClass", instance.propertyGetterType)
		assertEquals("propertyGetterMember", instance.propertyGetterMember)
		assertEquals(21, instance.propertyGetterLine)
	}

	@Test fun propertySetter() {
		// Write triggers persisting cited values.
		instance.propertySetterFile = "dummy"
		instance.propertySetterType = "dummy"
		instance.propertySetterMember = "dummy"
		instance.propertySetterLine = -1

		assertEquals("class.kt", instance.propertySetterFile)
		assertEquals("AClass", instance.propertySetterType)
		assertEquals("propertySetterMember", instance.propertySetterMember)
		assertEquals(37, instance.propertySetterLine)
	}
}
