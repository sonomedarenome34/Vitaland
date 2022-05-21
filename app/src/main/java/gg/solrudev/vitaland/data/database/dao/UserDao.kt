package gg.solrudev.vitaland.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import gg.solrudev.vitaland.data.database.model.ParentWithChildren
import gg.solrudev.vitaland.data.database.model.UserModel
import gg.solrudev.vitaland.data.database.model.UserWithRatings
import gg.solrudev.vitaland.data.database.model.UserWithShifts

@Dao
interface UserDao : GenericDao<UserModel> {

	@Query("SELECT * FROM users WHERE id = :userId")
	suspend fun getById(userId: Int): UserModel?

	@Transaction
	@Query("SELECT * FROM users_shifts WHERE :userId = user_id")
	suspend fun getShiftsById(userId: Int): UserWithShifts?

	@Transaction
	@Query("SELECT * FROM shifts_ratings WHERE :userId = user_id")
	suspend fun getRatingsById(userId: Int): UserWithRatings?

	@Transaction
	@Query("SELECT * FROM users WHERE :userId = id")
	suspend fun getChildrenById(userId: Int): ParentWithChildren?

	@Query("UPDATE users SET first_name = :firstName, last_name = :lastName, patronymic = :patronymic WHERE id = :userId")
	suspend fun updateNameById(userId: Int, firstName: String, lastName: String, patronymic: String)

	@Query("UPDATE users SET email = :email WHERE id = :userId")
	suspend fun updateEmailById(userId: Int, email: String)

	@Query("UPDATE users SET phone = :phone WHERE id = :userId")
	suspend fun updatePhoneById(userId: Int, phone: String)
}