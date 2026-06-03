package com.example.cite

import com.jakewharton.cite.__FILE__
import com.jakewharton.cite.__FQ_TYPE__
import com.jakewharton.cite.__LINE__
import com.jakewharton.cite.__MEMBER__
import com.jakewharton.cite.__TYPE__

object PropertyDelegates {
	val delegateFile by lazy { __FILE__ }
	val delegateFqType by lazy { __FQ_TYPE__ }
	val delegateType by lazy { __TYPE__ }
	val delegateLine by lazy { __LINE__ }

	fun locals(): LocalPropertyAccessor {
		val localDelegateFile by lazy { __FILE__ }
		val localDelegateFqType by lazy { __FQ_TYPE__ }
		val localDelegateType by lazy { __TYPE__ }
		val localDelegateMember by lazy { __MEMBER__ }
		val localDelegateLine by lazy { __LINE__ }

		return object : LocalPropertyAccessor {
			override val delegateFile get() = localDelegateFile
			override val delegateFqType get() = localDelegateFqType
			override val delegateType get() = localDelegateType
			override val delegateMember get() = localDelegateMember
			override val delegateLine get() = localDelegateLine
		}
	}
}

interface LocalPropertyAccessor {
	val delegateFile: String
	val delegateFqType: String
	val delegateType: String
	val delegateMember: String
	val delegateLine: Int
}
