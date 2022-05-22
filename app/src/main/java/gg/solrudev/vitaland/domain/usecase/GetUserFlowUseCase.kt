package gg.solrudev.vitaland.domain.usecase

import gg.solrudev.vitaland.domain.model.User
import gg.solrudev.vitaland.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetUserFlowUseCase {
	operator fun invoke(user: User): Flow<User>
}

class GetUserFlowUseCaseImpl @Inject constructor(
	private val userRepository: UserRepository
) : GetUserFlowUseCase {
	override fun invoke(user: User) = userRepository.getUserFlowById(user.id)
}