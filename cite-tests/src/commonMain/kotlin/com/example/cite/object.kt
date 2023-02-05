package com.example.cite

import com.jakewharton.cite.__FILE__
import com.jakewharton.cite.__LINE__
import com.jakewharton.cite.__MEMBER__
import com.jakewharton.cite.__TYPE__

object AnObject {
	fun funFile() = __FILE__
	fun funType() = __TYPE__
	fun funMember() = __MEMBER__
	fun funLine() = __LINE__

	val propertyInitializerFile = __FILE__
	val propertyInitializerType = __TYPE__
	val propertyInitializerLine = __LINE__

	val propertyGetterFile get() = __FILE__
	val propertyGetterType get() = __TYPE__
	val propertyGetterMember get() = __MEMBER__
	val propertyGetterLine get() = __LINE__

	var propertySetterFile: String = ""
		set(_) {
			field = __FILE__
		}
	var propertySetterType: String = ""
		set(_) {
			field = __TYPE__
		}
	var propertySetterMember: String = ""
		set(_) {
			field = __MEMBER__
		}
	var propertySetterLine: Int = Int.MIN_VALUE
		set(_) {
			field = __LINE__
		}
}
