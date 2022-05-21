package gg.solrudev.vitaland.data.repository

import gg.solrudev.vitaland.data.database.dao.ShiftDao
import gg.solrudev.vitaland.data.database.dao.UserDao
import gg.solrudev.vitaland.data.database.model.mapper.UserMapper
import gg.solrudev.vitaland.domain.model.Shift
import gg.solrudev.vitaland.domain.model.ShiftRating
import gg.solrudev.vitaland.domain.model.User
import gg.solrudev.vitaland.domain.repository.ShiftRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class ShiftRepositoryImpl @Inject constructor(
	private val shiftDao: ShiftDao,
	private val userDao: UserDao,
	private val userMapper: UserMapper
) : ShiftRepository {

	override suspend fun getUsersForShift(shift: Shift): List<User> {
		val userIds = shiftDao.getUsersById(shift.id) ?: return emptyList()
		return coroutineScope {
			userIds.map {
				async {
					val user = userDao.getById(it) ?: return@async null
					userMapper(user)
				}
			}
				.awaitAll()
				.filterNotNull()
		}
	}

	override suspend fun getRatingsForShift(shift: Shift): List<ShiftRating> {
		val ratings = shiftDao.getRatingsById(shift.id) ?: return emptyList()
		return coroutineScope {
			ratings.map {
				async {
					val user = userDao.getById(it.userId) ?: return@async null
					ShiftRating(userMapper(user), shift, it.rating, it.text)
				}
			}
				.awaitAll()
				.filterNotNull()
		}
	}

	override suspend fun updateShiftName(shift: Shift, name: String) {
		shiftDao.updateNameById(shift.id, name)
	}

	override suspend fun bindUserToShift(user: User, shift: Shift) {
		shiftDao.addUserById(shift.id, user.id)
	}

	override suspend fun unbindUserFromShift(user: User, shift: Shift) {
		shiftDao.deleteUserById(shift.id, user.id)
	}
}