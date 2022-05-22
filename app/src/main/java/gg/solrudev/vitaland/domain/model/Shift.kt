package gg.solrudev.vitaland.domain.model

import java.util.*

data class Shift(
	val id: Int = 0,
	val name: String = "",
	val startDate: Date = Date(0),
	val endDate: Date = Date(0)
) {

	companion object {
		val empty = Shift()
	}
}