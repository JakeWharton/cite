package com.example.cite

import com.jakewharton.cite.__FILE__
import com.jakewharton.cite.__LINE__
import com.jakewharton.cite.__MEMBER__
import com.jakewharton.cite.__TYPE__

enum class AnEnum {
	Normal,
	Subtype {
		override fun funFile() = __FILE__
		override fun funType() = __TYPE__
		override fun funMember() = __MEMBER__
		override fun funLine() = __LINE__

		override val propertyInitializerFile = __FILE__
		override val propertyInitializerType = __TYPE__
		override val propertyInitializerLine = __LINE__

		override val propertyGetterFile get() = __FILE__
		override val propertyGetterType get() = __TYPE__
		override val propertyGetterMember get() = __MEMBER__
		override val propertyGetterLine get() = __LINE__

		// Please never put mutable variables inside enums!
		override var propertySetterFile: String = ""
			set(_) {
				field = __FILE__
			}
		override var propertySetterType: String = ""
			set(_) {
				field = __TYPE__
			}
		override var propertySetterMember: String = ""
			set(_) {
				field = __MEMBER__
			}
		override var propertySetterLine: Int = Int.MIN_VALUE
			set(_) {
				field = __LINE__
			}
	},
	;

	open fun funFile() = __FILE__
	open fun funType() = "" // TODO __TYPE__
	open fun funMember() = __MEMBER__
	open fun funLine() = __LINE__

	open val propertyInitializerFile = __FILE__
	open val propertyInitializerType = "" // TODO __TYPE__
	open val propertyInitializerLine = __LINE__

	open val propertyGetterFile get() = __FILE__
	open val propertyGetterType get() = "" // TODO __TYPE__
	open val propertyGetterMember get() = __MEMBER__
	open val propertyGetterLine get() = __LINE__

	// Please never put mutable variables inside enums!
	open var propertySetterFile: String = ""
		set(_) {
			field = __FILE__
		}
	open var propertySetterType: String = ""
		set(_) {
			// TODO field = __TYPE__
		}
	open var propertySetterMember: String = ""
		set(_) {
			field = __MEMBER__
		}
	open var propertySetterLine: Int = Int.MIN_VALUE
		set(_) {
			field = __LINE__
		}
}
