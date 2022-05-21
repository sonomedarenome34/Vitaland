package gg.solrudev.vitaland.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gg.solrudev.vitaland.domain.usecase.GetChildUserUseCase
import gg.solrudev.vitaland.domain.usecase.GetCounselorUserUseCase
import gg.solrudev.vitaland.domain.usecase.GetEmployeeUserUseCase
import gg.solrudev.vitaland.domain.usecase.GetParentUserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val getParentUserUseCase: GetParentUserUseCase,
	private val getChildUserUseCase: GetChildUserUseCase,
	private val getCounselorUserUseCase: GetCounselorUserUseCase,
	private val getEmployeeUserUseCase: GetEmployeeUserUseCase
) : ViewModel(), Flow<LoginUiState> {

	private val uiState = MutableStateFlow(LoginUiState())

	override suspend fun collect(collector: FlowCollector<LoginUiState>) = uiState.collect(collector)

	fun loginAsParent() {
		viewModelScope.launch {
			val parentUser = getParentUserUseCase()
			uiState.update {
				it.copy(user = parentUser)
			}
		}
	}

	fun loginAsChild() {
		viewModelScope.launch {
			val childUser = getChildUserUseCase()
			uiState.update {
				it.copy(user = childUser)
			}
		}
	}

	fun loginAsCounselor() {
		viewModelScope.launch {
			val counselorUser = getCounselorUserUseCase()
			uiState.update {
				it.copy(user = counselorUser)
			}
		}
	}

	fun loginAsEmployee() {
		viewModelScope.launch {
			val employeeUser = getEmployeeUserUseCase()
			uiState.update {
				it.copy(user = employeeUser)
			}
		}
	}

	fun navigatedToProfile() = uiState.update {
		it.copy(user = null)
	}
}