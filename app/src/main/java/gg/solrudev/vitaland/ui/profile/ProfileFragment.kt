package gg.solrudev.vitaland.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import gg.solrudev.vitaland.R
import gg.solrudev.vitaland.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.fragment_profile) {

	private val binding by viewBinding(FragmentProfileBinding::bind)
	private val viewModel by viewModels<ProfileViewModel>()
	private val args by navArgs<ProfileFragmentArgs>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		viewModel.setUser(args.user)
	}

	private fun observeUiState() {
		viewLifecycleOwner.lifecycleScope.launch {
			viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.collect {

				}
			}
		}
	}
}