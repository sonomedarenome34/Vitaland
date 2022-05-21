package gg.solrudev.vitaland.domain.usecase

import gg.solrudev.vitaland.domain.model.User
import gg.solrudev.vitaland.domain.repository.UserRepository
import javax.inject.Inject

interface GetEmployeeUserUseCase {
	suspend operator fun invoke(): User
}

class GetEmployeeUserUseCaseImpl @Inject constructor(
	private val userRepository: UserRepository
) : GetEmployeeUserUseCase {
	override suspend fun invoke() = userRepository.getUserById(5)!!
}