package gg.solrudev.vitaland.data.repository

import gg.solrudev.vitaland.data.database.dao.ShiftDao
import gg.solrudev.vitaland.data.database.dao.UserDao
import gg.solrudev.vitaland.data.database.model.mapper.ShiftMapper
import gg.solrudev.vitaland.data.database.model.mapper.UserMapper
import gg.solrudev.vitaland.domain.model.*
import gg.solrudev.vitaland.domain.repository.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
	private val userDao: UserDao,
	private val shiftDao: ShiftDao,
	private val shiftMapper: ShiftMapper,
	private val userMapper: UserMapper
) : UserRepository {

	override suspend fun getShiftsForUser(user: User): List<Shift> {
		val shiftIds = userDao.getShiftsById(user.id) ?: return emptyList()
		return coroutineScope {
			shiftIds.map {
				async {
					val shift = shiftDao.getById(it) ?: return@async null
					shiftMapper(shift)
				}
			}
				.awaitAll()
				.filterNotNull()
		}
	}

	override suspend fun getRatingsForUser(user: User): List<ShiftRating> {
		val ratings = userDao.getRatingsById(user.id) ?: return emptyList()
		return coroutineScope {
			ratings.map {
				async {
					val shift = shiftDao.getById(it.userId) ?: return@async null
					ShiftRating(user, shiftMapper(shift), it.rating, it.text)
				}
			}
				.awaitAll()
				.filterNotNull()
		}
	}

	override suspend fun getChildrenForUser(user: User): List<User> {
		if (user.role != UserRole.PARENT) {
			return emptyList()
		}
		val childrenIds = userDao.getChildrenById(user.id) ?: return emptyList()
		return coroutineScope {
			childrenIds.map {
				async {
					val child = userDao.getById(it) ?: return@async null
					userMapper(child)
				}
			}
				.awaitAll()
				.filterNotNull()
		}
	}

	override suspend fun updateUserName(user: User, name: PersonName) {
		userDao.updateNameById(user.id, name.firstName, name.lastName, name.patronymic)
	}

	override suspend fun updateUserEmail(user: User, email: String) {
		userDao.updateEmailById(user.id, email)
	}

	override suspend fun updateUserPhone(user: User, phone: String) {
		userDao.updatePhoneById(user.id, phone)
	}
}