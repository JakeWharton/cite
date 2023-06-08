package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class ObjectTest {
	@Test fun initializer() {
		assertThat(AnObject.instanceInitializerFile).isEqualTo("object.kt")
		assertThat(AnObject.instanceInitializerType).isEqualTo("AnObject")
		assertThat(AnObject.instanceInitializerMember).isEqualTo("<init>")
		assertThat(AnObject.instanceInitializerLine).isEqualTo(48)
	}

	@Test fun `fun`() {
		assertThat(AnObject.funFile()).isEqualTo("object.kt")
		assertThat(AnObject.funType()).isEqualTo("AnObject")
		assertThat(AnObject.funMember()).isEqualTo("funMember")
		assertThat(AnObject.funLine()).isEqualTo(12)
	}

	@Test fun propertyInitializer() {
		assertThat(AnObject.propertyInitializerFile).isEqualTo("object.kt")
		assertThat(AnObject.propertyInitializerType).isEqualTo("AnObject")
		assertThat(AnObject.propertyInitializerLine).isEqualTo(16)
	}

	@Test fun propertyGetter() {
		assertThat(AnObject.propertyGetterFile).isEqualTo("object.kt")
		assertThat(AnObject.propertyGetterType).isEqualTo("AnObject")
		assertThat(AnObject.propertyGetterMember).isEqualTo("propertyGetterMember")
		assertThat(AnObject.propertyGetterLine).isEqualTo(21)
	}

	@Test fun propertySetter() {
		// Write triggers persisting cited values.
		AnObject.propertySetterFile = "dummy"
		AnObject.propertySetterType = "dummy"
		AnObject.propertySetterMember = "dummy"
		AnObject.propertySetterLine = -1

		assertThat(AnObject.propertySetterFile).isEqualTo("object.kt")
		assertThat(AnObject.propertySetterType).isEqualTo("AnObject")
		assertThat(AnObject.propertySetterMember).isEqualTo("propertySetterMember")
		assertThat(AnObject.propertySetterLine).isEqualTo(37)
	}
}
