package gg.solrudev.vitaland.data.database.model

import androidx.room.*

@Entity(
	tableName = "users_shifts",
	indices = [Index("shift_id")],
	primaryKeys = ["user_id", "shift_id"],
	foreignKeys = [
		ForeignKey(
			entity = UserModel::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("user_id")
		),
		ForeignKey(
			entity = ShiftModel::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("shift_id")
		)
	]
)
data class UserShiftCrossRef(
	@ColumnInfo(name = "user_id") val userId: Int,
	@ColumnInfo(name = "shift_id") val shiftId: Int
)

data class UserWithShifts(
	@Embedded val user: UserModel,
	@Relation(
		parentColumn = "id",
		entityColumn = "id",
		associateBy = Junction(
			UserShiftCrossRef::class,
			parentColumn = "user_id",
			entityColumn = "shift_id"
		)
	)
	val shifts: List<ShiftModel>?
)

data class ShiftWithUsers(
	@Embedded val shift: ShiftModel,
	@Relation(
		parentColumn = "id",
		entityColumn = "id",
		associateBy = Junction(
			UserShiftCrossRef::class,
			parentColumn = "shift_id",
			entityColumn = "user_id"
		)
	)
	val users: List<UserModel>?
)
