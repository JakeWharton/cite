package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class InterfaceTest {
	@Test fun `fun`() {
		assertThat(AnInterface.Instance.funFile()).isEqualTo("interface.kt")
		assertThat(AnInterface.Instance.funType()).isEqualTo("AnInterface")
		assertThat(AnInterface.Instance.funMember()).isEqualTo("funMember")
		assertThat(AnInterface.Instance.funLine()).isEqualTo(12)
	}

	@Test fun propertyGetter() {
		assertThat(AnInterface.Instance.propertyGetterFile).isEqualTo("interface.kt")
		assertThat(AnInterface.Instance.propertyGetterType).isEqualTo("AnInterface")
		assertThat(AnInterface.Instance.propertyGetterMember).isEqualTo("propertyGetterMember")
		assertThat(AnInterface.Instance.propertyGetterLine).isEqualTo(17)
	}

	@Test fun propertySetter() {
		// Write triggers persisting cited values to companion properties.
		AnInterface.Instance.propertySetterFile = "dummy"
		AnInterface.Instance.propertySetterType = "dummy"
		AnInterface.Instance.propertySetterMember = "dummy"
		AnInterface.Instance.propertySetterLine = -1

		assertThat(AnInterface.setterFileValue).isEqualTo("interface.kt")
		assertThat(AnInterface.setterTypeValue).isEqualTo("AnInterface")
		assertThat(AnInterface.setterMemberValue).isEqualTo("propertySetterMember")
		assertThat(AnInterface.setterLineValue).isEqualTo(37)
	}
}
