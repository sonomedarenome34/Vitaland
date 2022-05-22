package gg.solrudev.vitaland.ui.profile.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gg.solrudev.vitaland.domain.model.User
import gg.solrudev.vitaland.domain.usecase.GetUserFlowUseCase
import gg.solrudev.vitaland.domain.usecase.UpdateUserUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
	private val getUserFlowUseCase: GetUserFlowUseCase,
	private val updateUserUseCase: UpdateUserUseCase
) : ViewModel(), Flow<EditProfileUiState> {

	private val uiState = MutableStateFlow(EditProfileUiState())

	override suspend fun collect(collector: FlowCollector<EditProfileUiState>) = uiState.collect(collector)

	fun loadUser(user: User) {
		viewModelScope.launch {
			uiState.update {
				it.copy(isLoading = true)
			}
			val currentUser = getUserFlowUseCase(user).firstOrNull()
			uiState.update {
				it.copy(
					isLoading = false,
					user = currentUser
				)
			}
		}
	}

	fun updateCurrentUserState(user: User) = uiState.update {
		it.copy(user = user)
	}

	fun persistUpdatedUser() {
		viewModelScope.launch {
			uiState.update {
				it.copy(isLoading = true)
			}
			uiState.value.user?.let {
				updateUserUseCase(it)
			}
			uiState.update {
				it.copy(
					isLoading = false,
					isUpdated = true
				)
			}
		}
	}
}