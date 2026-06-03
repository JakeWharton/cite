package com.example.cite

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class LambdasTest {
	@Test fun lambda() {
		assertThat(lambdaFile().invoke()).isEqualTo("lambdas.kt")
		assertThat(lambdaMember().invoke()).isEqualTo("lambdaMember")
		assertThat(lambdaLine().invoke()).isEqualTo(11)
	}

	@Test fun lambdaInType() {
		assertThat(LambdaType.lambdaFile().invoke()).isEqualTo("lambdas.kt")
		assertThat(LambdaType.lambdaFqType().invoke()).isEqualTo("com.example.cite.LambdaType")
		assertThat(LambdaType.lambdaType().invoke()).isEqualTo("LambdaType")
		assertThat(LambdaType.lambdaMember().invoke()).isEqualTo("lambdaMember")
		assertThat(LambdaType.lambdaLine().invoke()).isEqualTo(22)
	}

	@Test fun propertyReference() {
		assertThat(propertyReferenceFile().invoke()).isEqualTo("lambdas.kt")
		assertThat(propertyReferenceMember().invoke()).isEqualTo("propertyReferenceMember")
		assertThat(propertyReferenceLine().invoke()).isEqualTo(15)
	}

	@Test fun propertyReferenceInType() {
		assertThat(LambdaType.propertyReferenceFile().invoke()).isEqualTo("lambdas.kt")
		assertThat(LambdaType.propertyReferenceFqType().invoke()).isEqualTo("com.example.cite.LambdaType")
		assertThat(LambdaType.propertyReferenceType().invoke()).isEqualTo("LambdaType")
		assertThat(LambdaType.propertyReferenceMember().invoke()).isEqualTo("propertyReferenceMember")
		assertThat(LambdaType.propertyReferenceLine().invoke()).isEqualTo(28)
	}
}
