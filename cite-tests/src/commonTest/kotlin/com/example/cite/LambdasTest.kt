package com.example.cite

import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class LambdasTest {
	@Test fun lambda() {
		assertEquals("lambdas.kt", lambdaFile().invoke())
		// TODO should be "lambdaMember".
		assertEquals("<anonymous>", lambdaMember().invoke())
		assertEquals(10, lambdaLine().invoke())
	}

	@Ignore // TODO https://github.com/JakeWharton/cite/issues/7
	@Test fun propertyReference() {
		assertEquals("lambdas.kt", propertyReferenceFile().invoke())
		assertEquals("AnObject", propertyReferenceType().invoke())
		assertEquals("propertyReferenceMember", propertyReferenceMember().invoke())
		assertEquals(16, propertyReferenceLine().invoke())
	}
}
