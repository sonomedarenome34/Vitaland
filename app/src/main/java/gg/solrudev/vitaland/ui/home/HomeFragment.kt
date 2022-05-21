package gg.solrudev.vitaland.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import gg.solrudev.vitaland.R
import gg.solrudev.vitaland.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

	private val binding by viewBinding(FragmentHomeBinding::bind)
	private val homeViewModel by viewModels<HomeViewModel>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		val textView = binding.textHome
		homeViewModel.text.observe(viewLifecycleOwner) {
			textView.text = it
		}
	}
}