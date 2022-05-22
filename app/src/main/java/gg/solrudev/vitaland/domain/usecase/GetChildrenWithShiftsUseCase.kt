package gg.solrudev.vitaland.domain.usecase

import gg.solrudev.vitaland.domain.model.ChildWithShift
import gg.solrudev.vitaland.domain.model.Shift
import gg.solrudev.vitaland.domain.model.User
import gg.solrudev.vitaland.domain.repository.UserRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

interface GetChildrenWithShiftsUseCase {
	suspend operator fun invoke(parent: User): List<ChildWithShift>
}

class GetChildrenWithShiftsUseCaseImpl @Inject constructor(
	private val userRepository: UserRepository
) : GetChildrenWithShiftsUseCase {

	override suspend fun invoke(parent: User) = coroutineScope {
		userRepository
			.getChildrenForUser(parent)
			.map { child ->
				async {
					val shift = userRepository
						.getShiftsForUser(child)
						.firstOrNull() ?: Shift.empty
					ChildWithShift(child, shift)
				}
			}
			.awaitAll()
	}
}