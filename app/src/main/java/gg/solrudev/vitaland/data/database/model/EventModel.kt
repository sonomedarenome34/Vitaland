package gg.solrudev.vitaland.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "events")
data class EventModel(
	@PrimaryKey(autoGenerate = true) val id: Int = 0,
	val name: String,
	val date: Date,
	@ColumnInfo(name = "shift_id") val shiftId: Int
)
