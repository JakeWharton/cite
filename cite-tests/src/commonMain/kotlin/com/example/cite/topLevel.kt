package com.example.cite

import com.jakewharton.cite.__FILE__
import com.jakewharton.cite.__LINE__
import com.jakewharton.cite.__MEMBER__

fun topLevelFunFile() = __FILE__
fun topLevelFunMember() = __MEMBER__
fun topLevelFunLine() = __LINE__

val topLevelPropertyInitializerFile = __FILE__
val topLevelPropertyInitializerLine = __LINE__

val topLevelPropertyGetterFile get() = __FILE__
val topLevelPropertyGetterMember get() = __MEMBER__
val topLevelPropertyGetterLine get() = __LINE__

var topLevelPropertySetterFile: String = ""
	set(_) {
		field = __FILE__
	}
var topLevelPropertySetterMember: String = ""
	set(_) {
		field = __MEMBER__
	}
var topLevelPropertySetterLine: Int = Int.MIN_VALUE
	set(_) {
		field = __LINE__
	}
