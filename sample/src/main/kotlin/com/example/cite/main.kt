@file:JvmName("Main")

package com.example.cite

import com.jakewharton.cite.__FILE__
import com.jakewharton.cite.__LINE__
import com.jakewharton.cite.__MEMBER__
import com.jakewharton.cite.__TYPE__

fun main() {
	Greeter.sayHi()
}

object Greeter {
	fun sayHi() {
		println("Hello from:")
		println("  File: $__FILE__")
		println("  Type: $__TYPE__")
		println("  Member: $__MEMBER__")
		println("  Line: $__LINE__")
	}
}
