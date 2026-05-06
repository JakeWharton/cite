package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class InterfaceTest {
	@Test fun `fun`() {
		assertThat(AnInterface.Instance.funFile()).isEqualTo("interface.kt")
		assertThat(AnInterface.Instance.funFqType()).isEqualTo("com.example.cite.AnInterface")
		assertThat(AnInterface.Instance.funType()).isEqualTo("AnInterface")
		assertThat(AnInterface.Instance.funMember()).isEqualTo("funMember")
		assertThat(AnInterface.Instance.funLine()).isEqualTo(14)
	}

	@Test fun propertyGetter() {
		assertThat(AnInterface.Instance.propertyGetterFile).isEqualTo("interface.kt")
		assertThat(AnInterface.Instance.propertyGetterFqType).isEqualTo("com.example.cite.AnInterface")
		assertThat(AnInterface.Instance.propertyGetterType).isEqualTo("AnInterface")
		assertThat(AnInterface.Instance.propertyGetterMember).isEqualTo("propertyGetterMember")
		assertThat(AnInterface.Instance.propertyGetterLine).isEqualTo(20)
	}

	@Test fun propertySetter() {
		// Write triggers persisting cited values to companion properties.
		AnInterface.Instance.propertySetterFile = "dummy"
		AnInterface.Instance.propertySetterFqType = "dummy"
		AnInterface.Instance.propertySetterType = "dummy"
		AnInterface.Instance.propertySetterMember = "dummy"
		AnInterface.Instance.propertySetterLine = -1

		assertThat(AnInterface.setterFileValue).isEqualTo("interface.kt")
		assertThat(AnInterface.setterFqTypeValue).isEqualTo("com.example.cite.AnInterface")
		assertThat(AnInterface.setterTypeValue).isEqualTo("AnInterface")
		assertThat(AnInterface.setterMemberValue).isEqualTo("propertySetterMember")
		assertThat(AnInterface.setterLineValue).isEqualTo(45)
	}
}
