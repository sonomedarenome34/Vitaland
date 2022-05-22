package gg.solrudev.vitaland.data.repository

import gg.solrudev.vitaland.data.database.dao.UserDao
import gg.solrudev.vitaland.data.database.model.mapper.ShiftMapper
import gg.solrudev.vitaland.data.database.model.mapper.UserMapper
import gg.solrudev.vitaland.domain.model.PersonName
import gg.solrudev.vitaland.domain.model.ShiftRating
import gg.solrudev.vitaland.domain.model.User
import gg.solrudev.vitaland.domain.model.UserRole
import gg.solrudev.vitaland.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
	private val userDao: UserDao,
	private val shiftMapper: ShiftMapper,
	private val userMapper: UserMapper
) : UserRepository {

	override suspend fun getShiftsForUser(user: User) = userDao
		.getShiftsById(user.id)
		?.flatMap { it.shifts ?: emptyList() }
		?.map(shiftMapper) ?: emptyList()

	override suspend fun getRatingsForUser(user: User) = userDao
		.getRatingsById(user.id)
		?.mapNotNull { shiftWithRatings ->
			shiftWithRatings.ratings?.map {
				ShiftRating(user, shiftMapper(shiftWithRatings.shift), it.rating, it.text)
			}
		}
		?.flatten() ?: emptyList()

	override suspend fun getChildrenForUser(user: User): List<User> {
		if (user.role != UserRole.PARENT) {
			return emptyList()
		}
		return userDao
			.getChildrenById(user.id)
			?.flatMap { it.children ?: emptyList() }
			?.map(userMapper) ?: emptyList()
	}

	override suspend fun updateUserName(user: User, name: PersonName) = userDao.updateNameById(
		user.id,
		name.firstName,
		name.lastName,
		name.patronymic
	)

	override suspend fun updateUserEmail(user: User, email: String) = userDao.updateEmailById(user.id, email)
	override suspend fun updateUserPhone(user: User, phone: String) = userDao.updatePhoneById(user.id, phone)
	override suspend fun getUserById(userId: Int) = userDao.getById(userId)?.let { userMapper(it) }

	override fun getUserFlowById(userId: Int) = userDao
		.getFlowById(userId)
		.filterNotNull()
		.map(userMapper)
}