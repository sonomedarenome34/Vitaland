package gg.solrudev.vitaland.domain.usecase

import gg.solrudev.vitaland.domain.model.User
import gg.solrudev.vitaland.domain.repository.UserRepository
import javax.inject.Inject

interface GetParentUserUseCase {
	suspend fun invoke(): User
}

class GetParentUserUseCaseImpl @Inject constructor(
	private val userRepository: UserRepository
) : GetChildUserUseCase {
	override suspend fun invoke() = userRepository.getUserById(1)!!
}