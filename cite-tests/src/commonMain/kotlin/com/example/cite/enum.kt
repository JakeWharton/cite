package com.example.cite

import com.jakewharton.cite.__FILE__
import com.jakewharton.cite.__FQ_TYPE__
import com.jakewharton.cite.__LINE__
import com.jakewharton.cite.__MEMBER__
import com.jakewharton.cite.__TYPE__

enum class AnEnum {
	Normal,
	Subtype {
		override fun funFile() = __FILE__
		override fun funFqType() = __FQ_TYPE__
		override fun funType() = __TYPE__
		override fun funMember() = __MEMBER__
		override fun funLine() = __LINE__

		override val propertyInitializerFile = __FILE__
		override val propertyInitializerFqType = __FQ_TYPE__
		override val propertyInitializerType = __TYPE__
		override val propertyInitializerLine = __LINE__

		override val propertyGetterFile get() = __FILE__
		override val propertyGetterFqType get() = __FQ_TYPE__
		override val propertyGetterType get() = __TYPE__
		override val propertyGetterMember get() = __MEMBER__
		override val propertyGetterLine get() = __LINE__

		// Please never put mutable variables inside enums!
		override var propertySetterFile: String = ""
			set(_) {
				field = __FILE__
			}
		override var propertySetterFqType: String = ""
			set(_) {
				field = __FQ_TYPE__
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

		override val instanceInitializerFile: String
		override val instanceInitializerFqType: String
		override val instanceInitializerType: String
		override val instanceInitializerMember: String
		override val instanceInitializerLine: Int
		init {
			instanceInitializerFile = __FILE__
			instanceInitializerFqType = __FQ_TYPE__
			instanceInitializerType = __TYPE__
			instanceInitializerMember = __MEMBER__
			instanceInitializerLine = __LINE__
		}
	},
	;

	open fun funFile() = __FILE__
	open fun funFqType() = __FQ_TYPE__
	open fun funType() = __TYPE__
	open fun funMember() = __MEMBER__
	open fun funLine() = __LINE__

	open val propertyInitializerFile = __FILE__
	open val propertyInitializerFqType = __FQ_TYPE__
	open val propertyInitializerType = __TYPE__
	open val propertyInitializerLine = __LINE__

	open val propertyGetterFile get() = __FILE__
	open val propertyGetterFqType get() = __FQ_TYPE__
	open val propertyGetterType get() = __TYPE__
	open val propertyGetterMember get() = __MEMBER__
	open val propertyGetterLine get() = __LINE__

	// Please never put mutable variables inside enums!
	open var propertySetterFile: String = ""
		set(_) {
			field = __FILE__
		}
	open var propertySetterFqType: String = ""
		set(_) {
			field = __FQ_TYPE__
		}
	open var propertySetterType: String = ""
		set(_) {
			field = __TYPE__
		}
	open var propertySetterMember: String = ""
		set(_) {
			field = __MEMBER__
		}
	open var propertySetterLine: Int = Int.MIN_VALUE
		set(_) {
			field = __LINE__
		}

	private val baseInitializerFile: String
	open val instanceInitializerFile: String get() = baseInitializerFile
	private val baseInitializerFqType: String
	open val instanceInitializerFqType: String get() = baseInitializerFqType
	private val baseInitializerType: String
	open val instanceInitializerType: String get() = baseInitializerType
	private val baseInitializerMember: String
	open val instanceInitializerMember: String get() = baseInitializerMember
	private val baseInitializerLine: Int
	open val instanceInitializerLine: Int get() = baseInitializerLine
	init {
		baseInitializerFile = __FILE__
		baseInitializerFqType = __FQ_TYPE__
		baseInitializerType = __TYPE__
		baseInitializerMember = __MEMBER__
		baseInitializerLine = __LINE__
	}
}
