package gg.solrudev.vitaland.data.database.model

import androidx.room.*

@Entity(tableName = "shifts_ratings", primaryKeys = ["user_id", "shift_id"])
data class ShiftRatingModel(
	@ColumnInfo(name = "user_id") val userId: Int,
	@ColumnInfo(name = "shift_id") val shiftId: Int,
	val rating: Double,
	val text: String
)
