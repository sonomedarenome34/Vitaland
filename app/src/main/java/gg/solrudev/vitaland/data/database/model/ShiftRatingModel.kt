package gg.solrudev.vitaland.data.database.model

import androidx.room.*

@Entity(tableName = "shifts_ratings", primaryKeys = ["user_id", "shift_id"])
data class ShiftRatingModel(
	@ColumnInfo(name = "user_id") val userId: Int,
	@ColumnInfo(name = "shift_id") val shiftId: Int,
	val rating: Double,
	val text: String
)

data class UserWithRatings(
	@Embedded val user: UserModel,
	@Relation(
		parentColumn = "user_id",
		entityColumn = "shift_id",
		associateBy = Junction(ShiftRatingModel::class)
	)
	val ratings: List<ShiftRatingModel>
)

data class RatingWithUsers(
	@Embedded val rating: ShiftRatingModel,
	@Relation(
		parentColumn = "shift_id",
		entityColumn = "user_id",
		associateBy = Junction(ShiftRatingModel::class)
	)
	val users: List<UserModel>
)
