package gg.solrudev.vitaland.ui.profile.edit

import gg.solrudev.vitaland.domain.model.User

data class EditProfileUiState(
	val user: User? = null,
	val isLoading: Boolean = true,
	val isUpdated: Boolean = false
)
