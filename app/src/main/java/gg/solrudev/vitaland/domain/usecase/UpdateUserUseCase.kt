package gg.solrudev.vitaland.domain.usecase

import gg.solrudev.vitaland.domain.model.User
import gg.solrudev.vitaland.domain.repository.UserRepository
import javax.inject.Inject

interface UpdateUserUseCase {
	suspend operator fun invoke(newUser: User)
}

class UpdateUserUseCaseImpl @Inject constructor(
	private val userRepository: UserRepository
) : UpdateUserUseCase {

	override suspend fun invoke(newUser: User) = userRepository.updateUser(newUser)
}