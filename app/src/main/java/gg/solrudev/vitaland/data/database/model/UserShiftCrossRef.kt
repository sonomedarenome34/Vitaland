package gg.solrudev.vitaland.data.database.model

import androidx.room.*

@Entity(tableName = "users_shifts", primaryKeys = ["user_id", "shift_id"])
data class UserShiftCrossRef(
	@ColumnInfo(name = "user_id") val userId: Int,
	@ColumnInfo(name = "shift_id") val shiftId: Int
)
