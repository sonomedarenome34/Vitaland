package gg.solrudev.vitaland.domain.model

data class ShiftRating(
	val user: User,
	val shift: Shift,
	val rating: Double,
	val text: String
)
