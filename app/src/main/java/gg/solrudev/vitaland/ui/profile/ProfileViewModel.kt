package gg.solrudev.vitaland.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gg.solrudev.vitaland.domain.model.User
import gg.solrudev.vitaland.domain.model.UserRole
import gg.solrudev.vitaland.domain.usecase.GetChildrenWithShiftsUseCase
import gg.solrudev.vitaland.domain.usecase.GetUserFlowUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
	private val getUserFlowUseCase: GetUserFlowUseCase,
	private val getChildrenWithShiftsUseCase: GetChildrenWithShiftsUseCase
) : ViewModel(), Flow<ProfileUiState> {

	private val uiState = MutableStateFlow(ProfileUiState())
	private var observeUserJob: Job? = null

	override suspend fun collect(collector: FlowCollector<ProfileUiState>) = uiState.collect(collector)

	fun observeUser(user: User) {
		observeUserJob = observeUserJob ?: viewModelScope.launch {
			launch {
				getUserFlowUseCase(user).collect { currentUser ->
					uiState.update {
						it.copy(
							isLoading = false,
							user = currentUser
						)
					}
				}
			}
			launch {
				if (user.role == UserRole.PARENT) {
					val childrenWithShifts = getChildrenWithShiftsUseCase(user)
					if (childrenWithShifts.isNotEmpty()) {
						uiState.update {
							it.copy(childrenList = childrenWithShifts)
						}
					}
				}
			}
		}
	}
}