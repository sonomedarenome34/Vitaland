package gg.solrudev.vitaland.data.database.model

import androidx.room.*

@Entity(tableName = "users_shifts", primaryKeys = ["user_id", "shift_id"])
data class UserShiftCrossRef(
	@ColumnInfo(name = "user_id") val userId: Int,
	@ColumnInfo(name = "shift_id") val shiftId: Int
)

data class UserWithShifts(
	@Embedded val user: UserModel,
	@Relation(
		parentColumn = "user_id",
		entityColumn = "shift_id",
		associateBy = Junction(UserShiftCrossRef::class)
	)
	val shifts: List<ShiftModel>?
)

data class ShiftWithUsers(
	@Embedded val shift: ShiftModel,
	@Relation(
		parentColumn = "shift_id",
		entityColumn = "user_id",
		associateBy = Junction(UserShiftCrossRef::class)
	)
	val users: List<UserModel>?
)
