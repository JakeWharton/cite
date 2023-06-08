package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class LambdasTest {
	@Test fun lambda() {
		assertThat(lambdaFile().invoke()).isEqualTo("lambdas.kt")
		assertThat(lambdaMember().invoke()).isEqualTo("lambdaMember")
		assertThat(lambdaLine().invoke()).isEqualTo(10)
	}

	@Test fun lambdaInType() {
		assertThat(LambdaType.lambdaFile().invoke()).isEqualTo("lambdas.kt")
		assertThat(LambdaType.lambdaType().invoke()).isEqualTo("LambdaType")
		assertThat(LambdaType.lambdaMember().invoke()).isEqualTo("lambdaMember")
		assertThat(LambdaType.lambdaLine().invoke()).isEqualTo(20)
	}

	@Test fun propertyReference() {
		assertThat(propertyReferenceFile().invoke()).isEqualTo("lambdas.kt")
		assertThat(propertyReferenceMember().invoke()).isEqualTo("propertyReferenceMember")
		assertThat(propertyReferenceLine().invoke()).isEqualTo(14)
	}

	@Test fun propertyReferenceInType() {
		assertThat(LambdaType.propertyReferenceFile().invoke()).isEqualTo("lambdas.kt")
		assertThat(LambdaType.propertyReferenceType().invoke()).isEqualTo("LambdaType")
		assertThat(LambdaType.propertyReferenceMember().invoke()).isEqualTo("propertyReferenceMember")
		assertThat(LambdaType.propertyReferenceLine().invoke()).isEqualTo(25)
	}
}
