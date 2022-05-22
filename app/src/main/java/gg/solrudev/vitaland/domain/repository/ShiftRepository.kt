package gg.solrudev.vitaland.domain.repository

import gg.solrudev.vitaland.domain.model.Event
import gg.solrudev.vitaland.domain.model.Shift
import gg.solrudev.vitaland.domain.model.ShiftRating
import gg.solrudev.vitaland.domain.model.User

interface ShiftRepository {
	suspend fun getUsersForShift(shift: Shift): List<User>
	suspend fun getRatingsForShift(shift: Shift): List<ShiftRating>
	suspend fun getEventsForShift(shift: Shift): List<Event>
	suspend fun updateShiftName(shift: Shift, name: String)
	suspend fun bindUserToShift(user: User, shift: Shift)
	suspend fun unbindUserFromShift(user: User, shift: Shift)
}