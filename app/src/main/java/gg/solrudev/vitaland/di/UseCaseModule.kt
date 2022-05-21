package gg.solrudev.vitaland.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import gg.solrudev.vitaland.domain.usecase.*

@InstallIn(ViewModelComponent::class)
@Module
interface UseCaseModule {

	@Binds
	fun bindGetParentUserUseCase(getParentUserUseCase: GetParentUserUseCaseImpl): GetParentUserUseCase

	@Binds
	fun bindGetChildUserUseCase(getChildUserUseCase: GetChildUserUseCaseImpl): GetChildUserUseCase

	@Binds
	fun bindGetEmployeeUserUseCase(getEmployeeUserUseCase: GetEmployeeUserUseCaseImpl): GetEmployeeUserUseCase

	@Binds
	fun bindGetCounselorUserUseCase(getCounselorUserUseCase: GetCounselorUserUseCaseImpl): GetCounselorUserUseCase
}
