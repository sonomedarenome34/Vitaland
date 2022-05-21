package gg.solrudev.vitaland.data.database.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

	@TypeConverter
	fun fromTimestamp(value: Long?) = value?.let { Date(it) }

	@TypeConverter
	fun dateToTimestamp(date: Date?) = date?.time
}