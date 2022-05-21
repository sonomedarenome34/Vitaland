package gg.solrudev.vitaland.domain.model

data class User(
	val id: Int,
	val firstName: String,
	val lastName: String,
	val patronymic: String,
	val role: UserRole
)
