package gg.solrudev.vitaland.data.database.model

import androidx.room.*

@Entity(
	tableName = "shifts_ratings",
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
data class ShiftRatingModel(
	@ColumnInfo(name = "user_id") val userId: Int,
	@ColumnInfo(name = "shift_id") val shiftId: Int,
	val rating: Double,
	val text: String
)

data class UserWithRatings(
	@Embedded val user: UserModel,
	@Relation(
		parentColumn = "id",
		entityColumn = "user_id",
		associateBy = Junction(
			ShiftRatingModel::class,
			parentColumn = "user_id",
			entityColumn = "user_id"
		)
	)
	val ratings: List<ShiftRatingModel>?
)

data class ShiftWithRatings(
	@Embedded val shift: ShiftModel,
	@Relation(
		parentColumn = "id",
		entityColumn = "shift_id",
		associateBy = Junction(
			ShiftRatingModel::class,
			parentColumn = "shift_id",
			entityColumn = "shift_id"
		)
	)
	val ratings: List<ShiftRatingModel>?
)
