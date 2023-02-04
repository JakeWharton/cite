package com.jakewharton.cite

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CiteTest {
	@Test fun fileThrowsByDefault() {
		val t = assertFailsWith<UnsupportedOperationException> { __FILE__ }
		assertEquals(
			"Property reference was not replaced by compiler. Did you apply Cite plugin?",
			t.message,
		)
	}

	@Test fun typeThrowsByDefault() {
		val t = assertFailsWith<UnsupportedOperationException> { __TYPE__ }
		assertEquals(
			"Property reference was not replaced by compiler. Did you apply Cite plugin?",
			t.message,
		)
	}

	@Test fun memberThrowsByDefault() {
		val t = assertFailsWith<UnsupportedOperationException> { __MEMBER__ }
		assertEquals(
			"Property reference was not replaced by compiler. Did you apply Cite plugin?",
			t.message,
		)
	}

	@Test fun lineThrowsByDefault() {
		val t = assertFailsWith<UnsupportedOperationException> { __LINE__ }
		assertEquals(
			"Property reference was not replaced by compiler. Did you apply Cite plugin?",
			t.message,
		)
	}
}
