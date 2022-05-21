package gg.solrudev.vitaland.domain.repository

import gg.solrudev.vitaland.domain.model.PersonName
import gg.solrudev.vitaland.domain.model.Shift
import gg.solrudev.vitaland.domain.model.ShiftRating
import gg.solrudev.vitaland.domain.model.User

interface UserRepository {
	suspend fun getShiftsForUser(user: User): List<Shift>
	suspend fun getRatingsForUser(user: User): List<ShiftRating>
	suspend fun getChildrenForUser(user: User): List<User>
	suspend fun updateUserName(user: User, name: PersonName)
	suspend fun updateUserEmail(user: User, email: String)
	suspend fun updateUserPhone(user: User, phone: String)
}