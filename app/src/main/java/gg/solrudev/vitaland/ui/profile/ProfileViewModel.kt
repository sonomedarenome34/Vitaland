package gg.solrudev.vitaland.ui.profile

import androidx.lifecycle.ViewModel
import gg.solrudev.vitaland.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow

class ProfileViewModel : ViewModel(), Flow<ProfileUiState> {

	private val uiState = MutableStateFlow(ProfileUiState())
	private lateinit var currentUser: User

	override suspend fun collect(collector: FlowCollector<ProfileUiState>) = uiState.collect(collector)

	fun setUser(user: User) {
		currentUser = user
	}
}