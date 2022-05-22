package gg.solrudev.vitaland.ui.profile

import gg.solrudev.vitaland.domain.model.ChildWithShift
import gg.solrudev.vitaland.domain.model.User

data class ProfileUiState(
	val isLoading: Boolean = true,
	val user: User? = null,
	val childrenList: List<ChildWithShift>? = null
)
