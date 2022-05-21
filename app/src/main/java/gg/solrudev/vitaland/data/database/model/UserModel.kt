package gg.solrudev.vitaland.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gg.solrudev.vitaland.domain.UserRole

@Entity(tableName = "users")
data class UserModel(
	@PrimaryKey(autoGenerate = true) val id: Int = 0,
	@ColumnInfo(name = "first_name") val firstName: String,
	@ColumnInfo(name = "last_name") val lastName: String = "",
	val patronymic: String = "",
	val role: UserRole
)
