package gg.solrudev.vitaland.di

import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gg.solrudev.vitaland.data.repository.ShiftRepositoryImpl
import gg.solrudev.vitaland.data.repository.UserRepositoryImpl
import gg.solrudev.vitaland.domain.repository.ShiftRepository
import gg.solrudev.vitaland.domain.repository.UserRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
interface RepositoryModule {

	@Binds
	@Singleton
	fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

	@Binds
	@Singleton
	fun bindShiftRepository(shiftRepositoryImpl: ShiftRepositoryImpl): ShiftRepository
}