package com.example.cite

import kotlin.test.Test
import kotlin.test.assertEquals

class EnumTest {
	@Test fun normalInitializer() {
		assertEquals("enum.kt", AnEnum.Normal.instanceInitializerFile)
		// TODO Should be just "AnEnum".
		assertEquals("", AnEnum.Normal.instanceInitializerType)
		// TODO assertEquals("<clinit>", AnEnum.Normal.instanceInitializerMember)
		assertEquals(100, AnEnum.Normal.instanceInitializerLine)
	}

	@Test fun normalFun() {
		assertEquals("enum.kt", AnEnum.Normal.funFile())
		// TODO assertEquals("AnEnum", AnEnum.Normal.funType())
		assertEquals("funMember", AnEnum.Normal.funMember())
		assertEquals(59, AnEnum.Normal.funLine())
	}

	@Test fun normalPropertyInitializer() {
		assertEquals("enum.kt", AnEnum.Normal.propertyInitializerFile)
		// TODO assertEquals("AnEnum", AnEnum.Normal.propertyInitializerType)
		assertEquals(63, AnEnum.Normal.propertyInitializerLine)
	}

	@Test fun normalPropertyGetter() {
		assertEquals("enum.kt", AnEnum.Normal.propertyGetterFile)
		// TODO assertEquals("AnEnum", AnEnum.Normal.propertyGetterType)
		// TODO Should be just "propertyGetterMember".
		assertEquals("<get-propertyGetterMember>", AnEnum.Normal.propertyGetterMember)
		assertEquals(68, AnEnum.Normal.propertyGetterLine)
	}

	@Test fun normalPropertySetter() {
		// Write triggers persisting cited values.
		AnEnum.Normal.propertySetterFile = "dummy"
		// TODO AnEnum.Normal.propertySetterType = "dummy"
		AnEnum.Normal.propertySetterMember = "dummy"
		AnEnum.Normal.propertySetterLine = -1

		assertEquals("enum.kt", AnEnum.Normal.propertySetterFile)
		// TODO assertEquals("AnEnum", AnEnum.Normal.propertySetterType)
		// TODO Should be just "propertySetterMember".
		assertEquals("<set-propertySetterMember>", AnEnum.Normal.propertySetterMember)
		assertEquals(85, AnEnum.Normal.propertySetterLine)
	}

	@Test fun subtypeInitializer() {
		assertEquals("enum.kt", AnEnum.Normal.instanceInitializerFile)
		// TODO Should be just "AnEnum".
		assertEquals("", AnEnum.Normal.instanceInitializerType)
		// TODO assertEquals("<clinit>", AnEnum.Normal.instanceInitializerMember)
		assertEquals(100, AnEnum.Normal.instanceInitializerLine)
	}

	@Test fun subtypeFun() {
		assertEquals("enum.kt", AnEnum.Subtype.funFile())
		assertEquals("AnEnum", AnEnum.Subtype.funType())
		assertEquals("funMember", AnEnum.Subtype.funMember())
		assertEquals(14, AnEnum.Subtype.funLine())
	}

	@Test fun subtypePropertyInitializer() {
		assertEquals("enum.kt", AnEnum.Subtype.propertyInitializerFile)
		assertEquals("AnEnum", AnEnum.Subtype.propertyInitializerType)
		assertEquals(18, AnEnum.Subtype.propertyInitializerLine)
	}

	@Test fun subtypePropertyGetter() {
		assertEquals("enum.kt", AnEnum.Subtype.propertyGetterFile)
		assertEquals("AnEnum", AnEnum.Subtype.propertyGetterType)
		// TODO Should be just "propertyGetterMember".
		assertEquals("<get-propertyGetterMember>", AnEnum.Subtype.propertyGetterMember)
		assertEquals(23, AnEnum.Subtype.propertyGetterLine)
	}

	@Test fun subtypePropertySetter() {
		// Write triggers persisting cited values.
		AnEnum.Subtype.propertySetterFile = "dummy"
		AnEnum.Subtype.propertySetterType = "dummy"
		AnEnum.Subtype.propertySetterMember = "dummy"
		AnEnum.Subtype.propertySetterLine = -1

		assertEquals("enum.kt", AnEnum.Subtype.propertySetterFile)
		assertEquals("AnEnum", AnEnum.Subtype.propertySetterType)
		// TODO Should be just "propertySetterMember".
		assertEquals("<set-propertySetterMember>", AnEnum.Subtype.propertySetterMember)
		assertEquals(40, AnEnum.Subtype.propertySetterLine)
	}
}
