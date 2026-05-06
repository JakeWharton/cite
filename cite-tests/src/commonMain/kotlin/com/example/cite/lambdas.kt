package com.example.cite

import com.jakewharton.cite.__FILE__
import com.jakewharton.cite.__FQ_TYPE__
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
	fun lambdaFile(): () -> String = { __FILE__ }
	fun lambdaFqType(): () -> String = { __FQ_TYPE__ }
	fun lambdaType(): () -> String = { __TYPE__ }
	fun lambdaMember(): () -> String = { __MEMBER__ }
	fun lambdaLine(): () -> Int = { __LINE__ }

	fun propertyReferenceFile(): () -> String = ::__FILE__
	fun propertyReferenceFqType(): () -> String = ::__FQ_TYPE__
	fun propertyReferenceType(): () -> String = ::__TYPE__
	fun propertyReferenceMember(): () -> String = ::__MEMBER__
	fun propertyReferenceLine(): () -> Int = ::__LINE__
}
