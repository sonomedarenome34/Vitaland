package gg.solrudev.vitaland.domain.model

data class ShiftRating(
	val user: User,
	val shift: Shift,
	val rating: Int,
	val text: String
)
