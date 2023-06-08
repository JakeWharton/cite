package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class PropertyDelegateTest {
	@Test fun classDelegates() {
		assertThat(PropertyDelegates.delegateFile).isEqualTo("propertyDelegates.kt")
		assertThat(PropertyDelegates.delegateType).isEqualTo("PropertyDelegates")
		assertThat(PropertyDelegates.delegateLine).isEqualTo(11)
	}

	@Test fun localDelegates() {
		val locals = PropertyDelegates.locals()
		assertThat(locals.delegateFile).isEqualTo("propertyDelegates.kt")
		assertThat(locals.delegateType).isEqualTo("PropertyDelegates")
		assertThat(locals.delegateMember).isEqualTo("locals")
		assertThat(locals.delegateLine).isEqualTo(17)
	}
}
