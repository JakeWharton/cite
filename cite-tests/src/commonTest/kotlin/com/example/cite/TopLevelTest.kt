package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class TopLevelTest {
	@Test fun `fun`() {
		assertThat(topLevelFunFile()).isEqualTo("topLevel.kt")
		assertThat(topLevelFunMember()).isEqualTo("topLevelFunMember")
		assertThat(topLevelFunLine()).isEqualTo(9)
	}

	@Test fun propertyInitializer() {
		assertThat(topLevelPropertyInitializerFile).isEqualTo("topLevel.kt")
		assertThat(topLevelPropertyInitializerLine).isEqualTo(12)
	}

	@Test fun propertyGetter() {
		assertThat(topLevelPropertyGetterFile).isEqualTo("topLevel.kt")
		assertThat(topLevelPropertyGetterMember).isEqualTo("topLevelPropertyGetterMember")
		assertThat(topLevelPropertyGetterLine).isEqualTo(16)
	}

	@Test fun propertySetter() {
		// Write triggers persisting cited values.
		topLevelPropertySetterFile = "dummy"
		topLevelPropertySetterMember = "dummy"
		topLevelPropertySetterLine = -1

		assertThat(topLevelPropertySetterFile).isEqualTo("topLevel.kt")
		assertThat(topLevelPropertySetterMember).isEqualTo("topLevelPropertySetterMember")
		assertThat(topLevelPropertySetterLine).isEqualTo(28)
	}
}
