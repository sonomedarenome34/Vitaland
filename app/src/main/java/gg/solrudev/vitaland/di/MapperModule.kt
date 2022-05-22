package gg.solrudev.vitaland.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gg.solrudev.vitaland.data.database.model.mapper.*

@InstallIn(SingletonComponent::class)
@Module
interface MapperModule {

	@Binds
	fun bindShiftMapper(shiftMapperImpl: ShiftMapperImpl): ShiftMapper

	@Binds
	fun bindUserMapper(userMapperImpl: UserMapperImpl): UserMapper

	@Binds
	fun bindEventMapper(eventMapperImpl: EventMapperImpl): EventMapper

	@Binds
	fun bindUserModelMapper(userModelMapperImpl: UserModelMapperImpl): UserModelMapper
}