package gg.solrudev.vitaland.domain.repository

import gg.solrudev.vitaland.domain.model.Shift
import gg.solrudev.vitaland.domain.model.ShiftRating
import gg.solrudev.vitaland.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
	suspend fun getShiftsForUser(user: User): List<Shift>
	suspend fun getRatingsForUser(user: User): List<ShiftRating>
	suspend fun getChildrenForUser(user: User): List<User>
	suspend fun updateUser(newUser: User)
	suspend fun getUserById(userId: Int): User?
	fun getUserFlowById(userId: Int): Flow<User>
}