package gg.solrudev.vitaland.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import gg.solrudev.vitaland.data.database.converter.DateConverter
import gg.solrudev.vitaland.data.database.dao.ShiftDao
import gg.solrudev.vitaland.data.database.dao.UserDao
import gg.solrudev.vitaland.data.database.model.*

@Database(
	entities = [
		UserModel::class,
		ShiftModel::class,
		ShiftRatingModel::class,
		ParentChildrenCrossRef::class,
		UserShiftCrossRef::class,
		EventModel::class
	],
	autoMigrations = [AutoMigration(from = 1, to = 2)],
	version = 2
)
@TypeConverters(DateConverter::class)
abstract class VitalandDatabase : RoomDatabase() {
	abstract fun userDao(): UserDao
	abstract fun shiftDao(): ShiftDao
}