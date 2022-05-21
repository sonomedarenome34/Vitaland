package gg.solrudev.vitaland.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Transaction
import gg.solrudev.vitaland.data.database.model.ShiftModel
import gg.solrudev.vitaland.data.database.model.ShiftWithUsers
import gg.solrudev.vitaland.data.database.model.UserWithRatings

@Dao
interface ShiftDao : GenericDao<ShiftModel> {

	@Transaction
	@RewriteQueriesToDropUnusedColumns
	@Query("SELECT * FROM shifts, users_shifts WHERE shift_id = :shiftId")
	suspend fun getUsersById(shiftId: Int): List<ShiftWithUsers>?

	@Transaction
	@RewriteQueriesToDropUnusedColumns
	@Query("SELECT * FROM users, shifts_ratings WHERE shift_id = :shiftId")
	suspend fun getRatingsById(shiftId: Int): List<UserWithRatings>?

	@Query("UPDATE shifts SET name = :name WHERE id = :shiftId")
	suspend fun updateNameById(shiftId: Int, name: String)

	@Query("DELETE FROM users_shifts WHERE shift_id = :shiftId AND user_id = :userId")
	suspend fun deleteUserById(shiftId: Int, userId: Int)

	@Query("INSERT INTO users_shifts VALUES (:shiftId, :userId)")
	suspend fun addUserById(shiftId: Int, userId: Int)
}