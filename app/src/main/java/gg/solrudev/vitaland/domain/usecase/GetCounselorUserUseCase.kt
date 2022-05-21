package gg.solrudev.vitaland.domain.usecase

import gg.solrudev.vitaland.domain.model.User
import gg.solrudev.vitaland.domain.repository.UserRepository
import javax.inject.Inject

interface GetCounselorUserUseCase {
	suspend operator fun invoke(): User
}

class GetCounselorUserUseCaseImpl @Inject constructor(
	private val userRepository: UserRepository
) : GetCounselorUserUseCase {
	override suspend fun invoke() = userRepository.getUserById(4)!!
}