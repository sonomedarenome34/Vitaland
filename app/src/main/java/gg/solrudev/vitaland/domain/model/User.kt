package gg.solrudev.vitaland.domain.model

import java.io.Serializable

data class User(
	val id: Int,
	val name: PersonName,
	val email: String,
	val phone: String,
	val role: UserRole
) : Serializable
