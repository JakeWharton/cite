package com.example.cite

import kotlin.test.Test
import kotlin.test.assertEquals

class LambdasTest {
	@Test fun lambda() {
		assertEquals("lambdas.kt", lambdaFile().invoke())
		assertEquals("lambdaMember", lambdaMember().invoke())
		assertEquals(10, lambdaLine().invoke())
	}

	@Test fun lambdaInType() {
		assertEquals("lambdas.kt", LambdaType.lambdaFile().invoke())
		assertEquals("LambdaType", LambdaType.lambdaType().invoke())
		assertEquals("lambdaMember", LambdaType.lambdaMember().invoke())
		assertEquals(20, LambdaType.lambdaLine().invoke())
	}

	@Test fun propertyReference() {
		assertEquals("lambdas.kt", propertyReferenceFile().invoke())
		assertEquals("propertyReferenceMember", propertyReferenceMember().invoke())
		// TODO assertEquals(16, propertyReferenceLine().invoke())
	}

	@Test fun propertyReferenceInType() {
		assertEquals("lambdas.kt", LambdaType.propertyReferenceFile().invoke())
		assertEquals("LambdaType", LambdaType.propertyReferenceType().invoke())
		assertEquals("propertyReferenceMember", LambdaType.propertyReferenceMember().invoke())
		// TODO assertEquals(16, LambdaType.propertyReferenceLine().invoke())
	}
}
