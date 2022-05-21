package gg.solrudev.vitaland.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import gg.solrudev.vitaland.data.database.model.ShiftModel
import gg.solrudev.vitaland.data.database.model.ShiftRatingModel

@Dao
interface ShiftDao : GenericDao<ShiftModel> {

	@Query("SELECT * FROM shifts WHERE id = :shiftId")
	suspend fun getById(shiftId: Int): ShiftModel?

	@Query("SELECT user_id FROM users_shifts WHERE shift_id = :shiftId")
	suspend fun getUsersById(shiftId: Int): List<Int>?

	@Query("SELECT * FROM shifts_ratings WHERE shift_id = :shiftId")
	suspend fun getRatingsById(shiftId: Int): List<ShiftRatingModel>?

	@Query("UPDATE shifts SET name = :name WHERE id = :shiftId")
	suspend fun updateNameById(shiftId: Int, name: String)

	@Query("DELETE FROM users_shifts WHERE user_id = :userId AND shift_id = :shiftId")
	suspend fun deleteUserById(shiftId: Int, userId: Int)

	@Query("INSERT INTO users_shifts VALUES (:shiftId, :userId)")
	suspend fun addUserById(shiftId: Int, userId: Int)
}