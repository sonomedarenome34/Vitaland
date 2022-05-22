package gg.solrudev.vitaland.ui.profile

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import gg.solrudev.vitaland.R
import gg.solrudev.vitaland.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

	private val binding by viewBinding(FragmentProfileBinding::bind)
	private val viewModel by viewModels<ProfileViewModel>()
	private val args by navArgs<ProfileFragmentArgs>()
	private val childrenListAdapter = ChildrenListAdapter()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.buttonProfileEdit.isVisible = args.self
		binding.buttonProfileEdit.setOnClickListener {
			val openEditProfile = ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(args.user)
			findNavController().navigate(openEditProfile)
		}
		binding.recyclerViewProfileChildren.adapter = childrenListAdapter
		binding.recyclerViewProfileChildren.addItemDecoration(
			DividerItemDecoration(
				requireContext(),
				LinearLayoutManager.VERTICAL
			)
		)
		viewModel.observeUser(args.user)
		observeUiState()
	}

	private fun observeUiState() {
		viewLifecycleOwner.lifecycleScope.launch {
			viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.collect {
					binding.progressBarProfileLoading.isVisible = it.isLoading
					it.user?.run {
						binding.textViewProfileName.text =
							getString(R.string.profile_displayed_name, name.firstName, name.lastName)
						binding.textViewProfileEmail.text = email
						binding.textViewProfilePhone.text = phone
					}
					it.childrenList?.let { children ->
						binding.groupProfileChildren.isVisible = true
						childrenListAdapter.submitList(children)
					}
				}
			}
		}
	}
}