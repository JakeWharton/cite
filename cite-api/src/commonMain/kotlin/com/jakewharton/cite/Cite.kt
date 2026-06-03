@file:JvmName("Cite")

package com.jakewharton.cite

import kotlin.jvm.JvmName
import kotlin.jvm.JvmSynthetic

@get:JvmSynthetic
@get:JvmName("__MODULE__")
public val __MODULE__: String get() = intrinsicFailure()

@get:JvmSynthetic
@get:JvmName("__FILE__")
public val __FILE__: String get() = intrinsicFailure()

/**
 * Replaced with the enclosing fully-qualified class name at compile-time.
 * If the declaration is top-level in a file, the synthetic name of the file facade will be returned.
 *
 * Note: If this is used on a top-level file declaration and the file contains `@file:JvmName`, this will reflect
 * that type name on the JVM only. Other targets will reflect
 */
@get:JvmSynthetic
@get:JvmName("__FQ_TYPE__")
public val __FQ_TYPE__: String get() = intrinsicFailure()

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
