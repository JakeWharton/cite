package com.jakewharton.cite

import assertk.assertFailure
import assertk.assertions.isEqualTo
import assertk.assertions.message
import kotlin.test.Test

class CiteTest {
	@Test fun fileThrowsByDefault() {
		assertFailure { __FILE__ }
			.message()
			.isEqualTo("Property reference was not replaced by compiler. Did you apply Cite plugin?")
	}

	@Test fun typeThrowsByDefault() {
		assertFailure { __TYPE__ }
			.message()
			.isEqualTo("Property reference was not replaced by compiler. Did you apply Cite plugin?")
	}

	@Test fun memberThrowsByDefault() {
		assertFailure { __MEMBER__ }
			.message()
			.isEqualTo("Property reference was not replaced by compiler. Did you apply Cite plugin?")
	}

	@Test fun lineThrowsByDefault() {
		assertFailure { __LINE__ }
			.message()
			.isEqualTo("Property reference was not replaced by compiler. Did you apply Cite plugin?")
	}
}
