package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class LocalClassTest {
	private val accessor = funWithClass()

	@Test fun initializer() {
		assertThat(accessor.instanceInitializerFile).isEqualTo("localClass.kt")
		assertThat(accessor.instanceInitializerType).isEqualTo("LocalClass")
		assertThat(accessor.instanceInitializerMember).isEqualTo("<init>")
		assertThat(accessor.instanceInitializerLine).isEqualTo(50)
	}

	@Test fun `fun`() {
		assertThat(accessor.funFile()).isEqualTo("localClass.kt")
		assertThat(accessor.funType()).isEqualTo("LocalClass")
		assertThat(accessor.funMember()).isEqualTo("funMember")
		assertThat(accessor.funLine()).isEqualTo(13)
	}

	@Test fun propertyInitializer() {
		assertThat(accessor.propertyInitializerFile).isEqualTo("localClass.kt")
		assertThat(accessor.propertyInitializerType).isEqualTo("LocalClass")
		assertThat(accessor.propertyInitializerMember).isEqualTo("funWithClass")
		assertThat(accessor.propertyInitializerLine).isEqualTo(18)
	}

	@Test fun propertyGetter() {
		assertThat(accessor.propertyGetterFile).isEqualTo("localClass.kt")
		assertThat(accessor.propertyGetterType).isEqualTo("LocalClass")
		assertThat(accessor.propertyGetterMember).isEqualTo("propertyGetterMember")
		assertThat(accessor.propertyGetterLine).isEqualTo(23)
	}

	@Test fun propertySetter() {
		// Write triggers persisting cited values.
		accessor.propertySetterFile = "dummy"
		accessor.propertySetterType = "dummy"
		accessor.propertySetterMember = "dummy"
		accessor.propertySetterLine = -1

		assertThat(accessor.propertySetterFile).isEqualTo("localClass.kt")
		assertThat(accessor.propertySetterType).isEqualTo("LocalClass")
		assertThat(accessor.propertySetterMember).isEqualTo("propertySetterMember")
		assertThat(accessor.propertySetterLine).isEqualTo(39)
	}
}
