@file:JvmName("Cite")

package com.jakewharton.cite

import kotlin.jvm.JvmName
import kotlin.jvm.JvmSynthetic

@get:JvmSynthetic
@get:JvmName("__FILE__")
public val __FILE__: String get() = intrinsicFailure()

@get:JvmSynthetic
@get:JvmName("__TYPE__")
public val __TYPE__: String get() = intrinsicFailure()

@get:JvmSynthetic
@get:JvmName("__MEMBER__")
public val __MEMBER__: String get() = intrinsicFailure()

@get:JvmSynthetic
@get:JvmName("__LINE__")
public val __LINE__: Int get() = intrinsicFailure()

private fun intrinsicFailure(): Nothing {
	throw UnsupportedOperationException(
		"Property reference was not replaced by compiler. Did you apply Cite plugin?")
}
