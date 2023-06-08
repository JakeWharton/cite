package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class ClassTest {
	private val instance = AClass()

	@Test fun initializer() {
		assertThat(instance.instanceInitializerFile).isEqualTo("class.kt")
		assertThat(instance.instanceInitializerType).isEqualTo("AClass")
		assertThat(instance.instanceInitializerMember).isEqualTo("<init>")
		assertThat(instance.instanceInitializerLine).isEqualTo(48)
	}

	@Test fun `fun`() {
		assertThat(instance.funFile()).isEqualTo("class.kt")
		assertThat(instance.funType()).isEqualTo("AClass")
		assertThat(instance.funMember()).isEqualTo("funMember")
		assertThat(instance.funLine()).isEqualTo(12)
	}

	@Test fun propertyInitializer() {
		assertThat(instance.propertyInitializerFile).isEqualTo("class.kt")
		assertThat(instance.propertyInitializerType).isEqualTo("AClass")
		assertThat(instance.propertyInitializerLine).isEqualTo(16)
	}

	@Test fun propertyGetter() {
		assertThat(instance.propertyGetterFile).isEqualTo("class.kt")
		assertThat(instance.propertyGetterType).isEqualTo("AClass")
		assertThat(instance.propertyGetterMember).isEqualTo("propertyGetterMember")
		assertThat(instance.propertyGetterLine).isEqualTo(21)
	}

	@Test fun propertySetter() {
		// Write triggers persisting cited values.
		instance.propertySetterFile = "dummy"
		instance.propertySetterType = "dummy"
		instance.propertySetterMember = "dummy"
		instance.propertySetterLine = -1

		assertThat(instance.propertySetterFile).isEqualTo("class.kt")
		assertThat(instance.propertySetterType).isEqualTo("AClass")
		assertThat(instance.propertySetterMember).isEqualTo("propertySetterMember")
		assertThat(instance.propertySetterLine).isEqualTo(37)
	}
}
