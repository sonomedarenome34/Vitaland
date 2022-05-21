package gg.solrudev.vitaland.domain.model

import java.util.*

data class Shift(
	val id: Int,
	val name: String,
	val startDate: Date,
	val endDate: Date
)