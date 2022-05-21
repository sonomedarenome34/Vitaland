package gg.solrudev.vitaland.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "shifts")
data class ShiftModel(
	@PrimaryKey(autoGenerate = true) val id: Int = 0,
	@ColumnInfo(name = "start_date") val startDate: Date,
	@ColumnInfo(name = "end_date") val endDate: Date,
	val name: String
)