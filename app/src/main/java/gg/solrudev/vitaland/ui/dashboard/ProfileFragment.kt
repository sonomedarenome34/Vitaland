package gg.solrudev.vitaland.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import gg.solrudev.vitaland.R
import gg.solrudev.vitaland.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

	private val binding by viewBinding(FragmentProfileBinding::bind)
	private val viewModel by viewModels<ProfileViewModel>()
	private val args by navArgs<ProfileFragmentArgs>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		val textView = binding.textDashboard
		viewModel.text.observe(viewLifecycleOwner) {
			textView.text = it
		}
	}
}