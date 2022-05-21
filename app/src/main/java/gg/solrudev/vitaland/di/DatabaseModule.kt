package gg.solrudev.vitaland.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gg.solrudev.vitaland.data.database.VitalandDatabase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

	@Provides
	@Singleton
	fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
		context,
		VitalandDatabase::class.java,
		"vitaland.db"
	).build()

	@Provides
	@Singleton
	fun provideUserDao(database: VitalandDatabase) = database.userDao()

	@Provides
	@Singleton
	fun provideShiftDao(database: VitalandDatabase) = database.shiftDao()
}