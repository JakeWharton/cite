package com.example.cite

import com.jakewharton.cite.__FILE__
import com.jakewharton.cite.__LINE__
import com.jakewharton.cite.__MEMBER__
import com.jakewharton.cite.__TYPE__

fun funWithClass(): LocalClassAccessor {
	class LocalClass {
		fun funFile() = __FILE__
		fun funType() = __TYPE__
		fun funMember() = __MEMBER__
		fun funLine() = __LINE__

		val propertyInitializerFile = __FILE__
		val propertyInitializerType = __TYPE__
		// TODO val propertyInitializerMember = __MEMBER__
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

		val instanceInitializerFile: String
		val instanceInitializerType: String
		// TODO val instanceInitializerMember: String
		val instanceInitializerLine: Int
		init {
			instanceInitializerFile = __FILE__
			instanceInitializerType = __TYPE__
			// TODO instanceInitializerMember = __MEMBER__
			instanceInitializerLine = __LINE__
		}
	}

	return object : LocalClassAccessor {
		val instance = LocalClass()

		override fun funFile() = instance.funFile()
		override fun funType() = instance.funType()
		override fun funMember() = instance.funMember()
		override fun funLine() = instance.funLine()

		override val propertyInitializerFile get() = instance.propertyInitializerFile
		override val propertyInitializerType get() = instance.propertyInitializerType
		// TODO override val propertyInitializerMember get() = instance.propertyInitializerMember
		override val propertyInitializerLine get() = instance.propertyInitializerLine

		override val propertyGetterFile get() = instance.propertyGetterFile
		override val propertyGetterType get() = instance.propertyGetterType
		override val propertyGetterMember get() = instance.propertyGetterMember
		override val propertyGetterLine get() = instance.propertyGetterLine

		override var propertySetterFile: String
			get() = instance.propertySetterFile
			set(value) { instance.propertySetterFile = value }
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
		override val instanceInitializerType get() = instance.instanceInitializerType
		override val instanceInitializerLine get() = instance.instanceInitializerLine
	}
}

interface LocalClassAccessor {
	fun funFile(): String
	fun funType(): String
	fun funMember(): String
	fun funLine(): Int

	val propertyInitializerFile: String
	val propertyInitializerType: String
	// TODO val propertyInitializerMember: String
	val propertyInitializerLine: Int

	val propertyGetterFile: String
	val propertyGetterType: String
	val propertyGetterMember: String
	val propertyGetterLine: Int

	var propertySetterFile: String
	var propertySetterType: String
	var propertySetterMember: String
	var propertySetterLine: Int

	val instanceInitializerFile: String
	val instanceInitializerType: String
	val instanceInitializerLine: Int
}
