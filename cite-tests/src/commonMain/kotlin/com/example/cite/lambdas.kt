package com.example.cite

import com.jakewharton.cite.__FILE__
import com.jakewharton.cite.__LINE__
import com.jakewharton.cite.__MEMBER__
import com.jakewharton.cite.__TYPE__

fun lambdaFile(): () -> String = { __FILE__ }
fun lambdaMember(): () -> String = { __MEMBER__ }
fun lambdaLine(): () -> Int = { __LINE__ }

fun propertyReferenceFile(): () -> String = ::__FILE__
fun propertyReferenceMember(): () -> String = ::__MEMBER__
fun propertyReferenceLine(): () -> Int = ::__LINE__

object LambdaType {
	fun lambdaType(): () -> String = { __TYPE__ }
	fun lambdaFile(): () -> String = { __FILE__ }
	fun lambdaMember(): () -> String = { __MEMBER__ }
	fun lambdaLine(): () -> Int = { __LINE__ }

	fun propertyReferenceFile(): () -> String = ::__FILE__
	fun propertyReferenceType(): () -> String = ::__TYPE__
	fun propertyReferenceMember(): () -> String = ::__MEMBER__
	fun propertyReferenceLine(): () -> Int = ::__LINE__
}
