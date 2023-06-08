package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class EnumTest {
	@Test fun normalInitializer() {
		assertThat(AnEnum.Normal.instanceInitializerFile).isEqualTo("enum.kt")
		assertThat(AnEnum.Normal.instanceInitializerType).isEqualTo("AnEnum")
		assertThat(AnEnum.Normal.instanceInitializerMember).isEqualTo("<init>")
		assertThat(AnEnum.Normal.instanceInitializerLine).isEqualTo(100)
	}

	@Test fun normalFun() {
		assertThat(AnEnum.Normal.funFile()).isEqualTo("enum.kt")
		assertThat(AnEnum.Normal.funType()).isEqualTo("AnEnum")
		assertThat(AnEnum.Normal.funMember()).isEqualTo("funMember")
		assertThat(AnEnum.Normal.funLine()).isEqualTo(59)
	}

	@Test fun normalPropertyInitializer() {
		assertThat(AnEnum.Normal.propertyInitializerFile).isEqualTo("enum.kt")
		assertThat(AnEnum.Normal.propertyInitializerType).isEqualTo("AnEnum")
		assertThat(AnEnum.Normal.propertyInitializerLine).isEqualTo(63)
	}

	@Test fun normalPropertyGetter() {
		assertThat(AnEnum.Normal.propertyGetterFile).isEqualTo("enum.kt")
		assertThat(AnEnum.Normal.propertyGetterType).isEqualTo("AnEnum")
		assertThat(AnEnum.Normal.propertyGetterMember).isEqualTo("propertyGetterMember")
		assertThat(AnEnum.Normal.propertyGetterLine).isEqualTo(68)
	}

	@Test fun normalPropertySetter() {
		// Write triggers persisting cited values.
		AnEnum.Normal.propertySetterFile = "dummy"
		AnEnum.Normal.propertySetterType = "dummy"
		AnEnum.Normal.propertySetterMember = "dummy"
		AnEnum.Normal.propertySetterLine = -1

		assertThat(AnEnum.Normal.propertySetterFile).isEqualTo("enum.kt")
		assertThat(AnEnum.Normal.propertySetterType).isEqualTo("AnEnum")
		assertThat(AnEnum.Normal.propertySetterMember).isEqualTo("propertySetterMember")
		assertThat(AnEnum.Normal.propertySetterLine).isEqualTo(85)
	}

	@Test fun subtypeInitializer() {
		assertThat(AnEnum.Normal.instanceInitializerFile).isEqualTo("enum.kt")
		assertThat(AnEnum.Normal.instanceInitializerType).isEqualTo("AnEnum")
		assertThat(AnEnum.Normal.instanceInitializerMember).isEqualTo("<init>")
		assertThat(AnEnum.Normal.instanceInitializerLine).isEqualTo(100)
	}

	@Test fun subtypeFun() {
		assertThat(AnEnum.Subtype.funFile()).isEqualTo("enum.kt")
		assertThat(AnEnum.Subtype.funType()).isEqualTo("AnEnum")
		assertThat(AnEnum.Subtype.funMember()).isEqualTo("funMember")
		assertThat(AnEnum.Subtype.funLine()).isEqualTo(14)
	}

	@Test fun subtypePropertyInitializer() {
		assertThat(AnEnum.Subtype.propertyInitializerFile).isEqualTo("enum.kt")
		assertThat(AnEnum.Subtype.propertyInitializerType).isEqualTo("AnEnum")
		assertThat(AnEnum.Subtype.propertyInitializerLine).isEqualTo(18)
	}

	@Test fun subtypePropertyGetter() {
		assertThat(AnEnum.Subtype.propertyGetterFile).isEqualTo("enum.kt")
		assertThat(AnEnum.Subtype.propertyGetterType).isEqualTo("AnEnum")
		assertThat(AnEnum.Subtype.propertyGetterMember).isEqualTo("propertyGetterMember")
		assertThat(AnEnum.Subtype.propertyGetterLine).isEqualTo(23)
	}

	@Test fun subtypePropertySetter() {
		// Write triggers persisting cited values.
		AnEnum.Subtype.propertySetterFile = "dummy"
		AnEnum.Subtype.propertySetterType = "dummy"
		AnEnum.Subtype.propertySetterMember = "dummy"
		AnEnum.Subtype.propertySetterLine = -1

		assertThat(AnEnum.Subtype.propertySetterFile).isEqualTo("enum.kt")
		assertThat(AnEnum.Subtype.propertySetterType).isEqualTo("AnEnum")
		assertThat(AnEnum.Subtype.propertySetterMember).isEqualTo("propertySetterMember")
		assertThat(AnEnum.Subtype.propertySetterLine).isEqualTo(40)
	}
}
