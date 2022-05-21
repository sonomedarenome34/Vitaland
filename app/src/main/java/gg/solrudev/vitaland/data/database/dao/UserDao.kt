package gg.solrudev.vitaland.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import gg.solrudev.vitaland.data.database.model.ShiftRatingModel
import gg.solrudev.vitaland.data.database.model.UserModel

@Dao
interface UserDao : GenericDao<UserModel> {

	@Query("SELECT * FROM users WHERE id = :userId")
	suspend fun getById(userId: Int): UserModel?

	@Query("SELECT shift_id FROM users_shifts WHERE :userId = user_id")
	suspend fun getShiftsById(userId: Int): List<Int>?

	@Query("SELECT * FROM shifts_ratings WHERE :userId = user_id")
	suspend fun getRatingsById(userId: Int): List<ShiftRatingModel>?

	@Query("SELECT child_id FROM parents_children WHERE parent_id = :userId")
	suspend fun getChildrenById(userId: Int): List<Int>?

	@Query("UPDATE users SET first_name = :firstName, last_name = :lastName, patronymic = :patronymic WHERE id = :userId")
	suspend fun updateNameById(userId: Int, firstName: String, lastName: String, patronymic: String)

	@Query("UPDATE users SET email = :email WHERE id = :userId")
	suspend fun updateEmailById(userId: Int, email: String)

	@Query("UPDATE users SET phone = :phone WHERE id = :userId")
	suspend fun updatePhoneById(userId: Int, phone: String)
}