package gg.solrudev.vitaland.domain.usecase

import gg.solrudev.vitaland.domain.model.User
import gg.solrudev.vitaland.domain.repository.UserRepository
import javax.inject.Inject

interface GetChildUserUseCase {
	suspend fun invoke(): User
}

class GetChildUserUseCaseImpl @Inject constructor(
	private val userRepository: UserRepository
) : GetChildUserUseCase {
	override suspend fun invoke() = userRepository.getUserById(2)!!
}