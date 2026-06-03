package com.example.cite

import com.jakewharton.cite.__FILE__
import com.jakewharton.cite.__FQ_TYPE__
import com.jakewharton.cite.__LINE__
import com.jakewharton.cite.__MEMBER__
import com.jakewharton.cite.__TYPE__

fun funWithClass(): LocalClassAccessor {
	class LocalClass {
		fun funFile() = __FILE__
		fun funFqType() = __FQ_TYPE__
		fun funType() = __TYPE__
		fun funMember() = __MEMBER__
		fun funLine() = __LINE__

		val propertyInitializerFile = __FILE__
		val propertyInitializerFqType = __FQ_TYPE__
		val propertyInitializerType = __TYPE__
		val propertyInitializerMember = __MEMBER__
		val propertyInitializerLine = __LINE__

		val propertyGetterFile get() = __FILE__
		val propertyGetterFqType get() = __FQ_TYPE__
		val propertyGetterType get() = __TYPE__
		val propertyGetterMember get() = __MEMBER__
		val propertyGetterLine get() = __LINE__

		var propertySetterFile: String = ""
			set(_) {
				field = __FILE__
			}
		var propertySetterFqType: String = ""
			set(_) {
				field = __FQ_TYPE__
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

		val instanceInitializerFile: String
		val instanceInitializerFqType: String
		val instanceInitializerType: String
		val instanceInitializerMember: String
		val instanceInitializerLine: Int
		init {
			instanceInitializerFile = __FILE__
			instanceInitializerFqType = __FQ_TYPE__
			instanceInitializerType = __TYPE__
			instanceInitializerMember = __MEMBER__
			instanceInitializerLine = __LINE__
		}
	}

	return object : LocalClassAccessor {
		val instance = LocalClass()

		override fun funFile() = instance.funFile()
		override fun funFqType() = instance.funFqType()
		override fun funType() = instance.funType()
		override fun funMember() = instance.funMember()
		override fun funLine() = instance.funLine()

		override val propertyInitializerFile get() = instance.propertyInitializerFile
		override val propertyInitializerFqType get() = instance.propertyInitializerFqType
		override val propertyInitializerType get() = instance.propertyInitializerType
		override val propertyInitializerMember get() = instance.propertyInitializerMember
		override val propertyInitializerLine get() = instance.propertyInitializerLine

		override val propertyGetterFile get() = instance.propertyGetterFile
		override val propertyGetterFqType get() = instance.propertyGetterFqType
		override val propertyGetterType get() = instance.propertyGetterType
		override val propertyGetterMember get() = instance.propertyGetterMember
		override val propertyGetterLine get() = instance.propertyGetterLine

		override var propertySetterFile: String
			get() = instance.propertySetterFile
			set(value) { instance.propertySetterFile = value }
		override var propertySetterFqType: String
			get() = instance.propertySetterFqType
			set(value) { instance.propertySetterFqType = value }
		override var propertySetterType: String
			get() = instance.propertySetterType
			set(value) { instance.propertySetterType = value }
		override var propertySetterMember: String
			get() = instance.propertySetterMember
			set(value) { instance.propertySetterMember = value }
		override var propertySetterLine: Int
			get() = instance.propertySetterLine
			set(value) { instance.propertySetterLine = value }

		override val instanceInitializerFile get() = instance.instanceInitializerFile
		override val instanceInitializerFqType get() = instance.instanceInitializerFqType
		override val instanceInitializerType get() = instance.instanceInitializerType
		override val instanceInitializerMember get() = instance.instanceInitializerMember
		override val instanceInitializerLine get() = instance.instanceInitializerLine
	}
}

interface LocalClassAccessor {
	fun funFile(): String
	fun funFqType(): String
	fun funType(): String
	fun funMember(): String
	fun funLine(): Int

	val propertyInitializerFile: String
	val propertyInitializerFqType: String
	val propertyInitializerType: String
	val propertyInitializerMember: String
	val propertyInitializerLine: Int

	val propertyGetterFile: String
	val propertyGetterFqType: String
	val propertyGetterType: String
	val propertyGetterMember: String
	val propertyGetterLine: Int

	var propertySetterFile: String
	var propertySetterFqType: String
	var propertySetterType: String
	var propertySetterMember: String
	var propertySetterLine: Int

	val instanceInitializerFile: String
	val instanceInitializerFqType: String
	val instanceInitializerType: String
	val instanceInitializerMember: String
	val instanceInitializerLine: Int
}
