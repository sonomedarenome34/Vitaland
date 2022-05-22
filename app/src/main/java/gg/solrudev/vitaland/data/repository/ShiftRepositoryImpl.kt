package gg.solrudev.vitaland.data.repository

import gg.solrudev.vitaland.data.database.dao.ShiftDao
import gg.solrudev.vitaland.data.database.model.mapper.EventMapper
import gg.solrudev.vitaland.data.database.model.mapper.UserMapper
import gg.solrudev.vitaland.domain.model.Shift
import gg.solrudev.vitaland.domain.model.ShiftRating
import gg.solrudev.vitaland.domain.model.User
import gg.solrudev.vitaland.domain.repository.ShiftRepository
import javax.inject.Inject

class ShiftRepositoryImpl @Inject constructor(
	private val shiftDao: ShiftDao,
	private val userMapper: UserMapper,
	private val eventMapper: EventMapper
) : ShiftRepository {

	override suspend fun getUsersForShift(shift: Shift) = shiftDao
		.getUsersById(shift.id)
		?.flatMap { it.users ?: emptyList() }
		?.map(userMapper) ?: emptyList()

	override suspend fun getRatingsForShift(shift: Shift) = shiftDao
		.getRatingsById(shift.id)
		?.mapNotNull { userWithRatings ->
			userWithRatings.ratings?.map {
				ShiftRating(userMapper(userWithRatings.user), shift, it.rating, it.text)
			}
		}
		?.flatten() ?: emptyList()

	override suspend fun getEventsForShift(shift: Shift) = shiftDao
		.getEventsById(shift.id)
		?.flatMap { it.events ?: emptyList() }
		?.map(eventMapper) ?: emptyList()

	override suspend fun updateShiftName(shift: Shift, name: String) = shiftDao.updateNameById(shift.id, name)
	override suspend fun bindUserToShift(user: User, shift: Shift) = shiftDao.addUserById(shift.id, user.id)
	override suspend fun unbindUserFromShift(user: User, shift: Shift) = shiftDao.deleteUserById(shift.id, user.id)
}