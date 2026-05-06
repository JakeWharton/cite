package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class ClassTest {
	private val instance = AClass()

	@Test fun initializer() {
		assertThat(instance.instanceInitializerFile).isEqualTo("class.kt")
		assertThat(instance.instanceInitializerFqType).isEqualTo("com.example.cite.AClass")
		assertThat(instance.instanceInitializerType).isEqualTo("AClass")
		assertThat(instance.instanceInitializerMember).isEqualTo("<init>")
		assertThat(instance.instanceInitializerLine).isEqualTo(58)
	}

	@Test fun `fun`() {
		assertThat(instance.funFile()).isEqualTo("class.kt")
		assertThat(instance.funFqType()).isEqualTo("com.example.cite.AClass")
		assertThat(instance.funType()).isEqualTo("AClass")
		assertThat(instance.funMember()).isEqualTo("funMember")
		assertThat(instance.funLine()).isEqualTo(14)
	}

	@Test fun propertyInitializer() {
		assertThat(instance.propertyInitializerFile).isEqualTo("class.kt")
		assertThat(instance.propertyInitializerFqType).isEqualTo("com.example.cite.AClass")
		assertThat(instance.propertyInitializerType).isEqualTo("AClass")
		assertThat(instance.propertyInitializerLine).isEqualTo(19)
	}

	@Test fun propertyGetter() {
		assertThat(instance.propertyGetterFile).isEqualTo("class.kt")
		assertThat(instance.propertyGetterFqType).isEqualTo("com.example.cite.AClass")
		assertThat(instance.propertyGetterType).isEqualTo("AClass")
		assertThat(instance.propertyGetterMember).isEqualTo("propertyGetterMember")
		assertThat(instance.propertyGetterLine).isEqualTo(25)
	}

	@Test fun propertySetter() {
		// Write triggers persisting cited values.
		instance.propertySetterFile = "dummy"
		instance.propertySetterFqType = "dummy"
		instance.propertySetterType = "dummy"
		instance.propertySetterMember = "dummy"
		instance.propertySetterLine = -1

		assertThat(instance.propertySetterFile).isEqualTo("class.kt")
		assertThat(instance.propertySetterFqType).isEqualTo("com.example.cite.AClass")
		assertThat(instance.propertySetterType).isEqualTo("AClass")
		assertThat(instance.propertySetterMember).isEqualTo("propertySetterMember")
		assertThat(instance.propertySetterLine).isEqualTo(45)
	}
}
