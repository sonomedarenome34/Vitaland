package gg.solrudev.vitaland.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import gg.solrudev.vitaland.data.database.model.ShiftModel
import gg.solrudev.vitaland.data.database.model.ShiftWithRatings
import gg.solrudev.vitaland.data.database.model.ShiftWithUsers

@Dao
interface ShiftDao : GenericDao<ShiftModel> {

	@Query("SELECT * FROM shifts WHERE id = :shiftId")
	suspend fun getById(shiftId: Int): ShiftModel?

	@Transaction
	@Query("SELECT * FROM users_shifts WHERE shift_id = :shiftId")
	suspend fun getUsersById(shiftId: Int): ShiftWithUsers?

	@Query("SELECT * FROM shifts_ratings WHERE shift_id = :shiftId")
	suspend fun getRatingsById(shiftId: Int): ShiftWithRatings?

	@Query("UPDATE shifts SET name = :name WHERE id = :shiftId")
	suspend fun updateNameById(shiftId: Int, name: String)

	@Query("DELETE FROM users_shifts WHERE user_id = :userId")
	suspend fun deleteUserById(shiftId: Int, userId: Int)

	@Query("INSERT INTO users_shifts VALUES (:shiftId, :userId)")
	suspend fun addUserById(shiftId: Int, userId: Int)
}