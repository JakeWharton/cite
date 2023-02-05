@file:JvmName("Main")

package com.example.cite

import com.jakewharton.cite.__FILE__
import com.jakewharton.cite.__LINE__
import com.jakewharton.cite.__MEMBER__
import com.jakewharton.cite.__TYPE__
import kotlin.jvm.JvmName

fun main() {
	Greeter.sayHi()
}

object Greeter {
	fun sayHi() {
		println("Hello: $__FILE__, $__TYPE__, $__MEMBER__, $__LINE__")
	}
}
