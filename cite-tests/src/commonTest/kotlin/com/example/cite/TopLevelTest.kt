package com.example.cite

import kotlin.test.Test
import kotlin.test.assertEquals

class TopLevelTest {
	@Test fun `fun`() {
		assertEquals("topLevel.kt", topLevelFunFile())
		assertEquals("topLevelFunMember", topLevelFunMember())
		assertEquals(9, topLevelFunLine())
	}

	@Test fun propertyInitializer() {
		assertEquals("topLevel.kt", topLevelPropertyInitializerFile)
		assertEquals(12, topLevelPropertyInitializerLine)
	}

	@Test fun propertyGetter() {
		assertEquals("topLevel.kt", topLevelPropertyGetterFile)
		// TODO Should be just "topLevelPropertyGetterMember".
		assertEquals("<get-topLevelPropertyGetterMember>", topLevelPropertyGetterMember)
		assertEquals(16, topLevelPropertyGetterLine)
	}

	@Test fun propertySetter() {
		// Write triggers persisting cited values.
		topLevelPropertySetterFile = "dummy"
		topLevelPropertySetterMember = "dummy"
		topLevelPropertySetterLine = -1

		assertEquals("topLevel.kt", topLevelPropertySetterFile)
		// TODO Should be just "topLevelPropertySetterMember".
		assertEquals("<set-topLevelPropertySetterMember>", topLevelPropertySetterMember)
		assertEquals(28, topLevelPropertySetterLine)
	}
}
