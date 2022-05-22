package gg.solrudev.vitaland.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import gg.solrudev.vitaland.MobileNavigationDirections
import gg.solrudev.vitaland.R
import gg.solrudev.vitaland.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

	private val binding by viewBinding(FragmentLoginBinding::bind)
	private val viewModel by viewModels<LoginViewModel>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		setupNavigation()
		observeUiState()
	}

	private fun setupNavigation() {
		binding.buttonLoginParent.setOnClickListener {
			viewModel.loginAsParent()
		}
		binding.buttonLoginChild.setOnClickListener {
			viewModel.loginAsChild()
		}
		binding.buttonLoginCounselor.setOnClickListener {
			viewModel.loginAsCounselor()
		}
		binding.buttonLoginEmployee.setOnClickListener {
			viewModel.loginAsEmployee()
		}
	}

	private fun observeUiState() {
		viewLifecycleOwner.lifecycleScope.launch {
			viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.collect {
					if (it.user != null) {
						val toProfileScreen = MobileNavigationDirections.actionGlobalProfile(it.user, self = true)
						findNavController().navigate(toProfileScreen)
						viewModel.navigatedToProfile()
					}
				}
			}
		}
	}
}