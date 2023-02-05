package com.example.cite

import com.jakewharton.cite.__FILE__
import com.jakewharton.cite.__LINE__
import com.jakewharton.cite.__MEMBER__
import com.jakewharton.cite.__TYPE__

interface AnInterface {
	fun funFile() = __FILE__
	fun funType() = __TYPE__
	fun funMember() = __MEMBER__
	fun funLine() = __LINE__

	val propertyGetterFile get() = __FILE__
	val propertyGetterType get() = __TYPE__
	val propertyGetterMember get() = __MEMBER__
	val propertyGetterLine get() = __LINE__

	var propertySetterFile: String
		get() = throw UnsupportedOperationException()
		set(_) {
			setterFileValue = __FILE__
		}
	var propertySetterType: String
		get() = throw UnsupportedOperationException()
		set(_) {
			setterTypeValue = __TYPE__
		}
	var propertySetterMember: String
		get() = throw UnsupportedOperationException()
		set(_) {
			setterMemberValue = __MEMBER__
		}
	var propertySetterLine: Int
		get() = throw UnsupportedOperationException()
		set(_) {
			setterLineValue = __LINE__
		}

	companion object {
		val Instance = object : AnInterface { }

		var setterFileValue: String = ""
		var setterTypeValue: String = ""
		var setterMemberValue: String = ""
		var setterLineValue: Int = Int.MIN_VALUE
	}
}
