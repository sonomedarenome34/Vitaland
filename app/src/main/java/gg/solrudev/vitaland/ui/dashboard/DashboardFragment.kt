package gg.solrudev.vitaland.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import gg.solrudev.vitaland.R
import gg.solrudev.vitaland.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

	private val binding by viewBinding(FragmentDashboardBinding::bind)
	private val dashboardViewModel by viewModels<DashboardViewModel>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		val textView = binding.textDashboard
		dashboardViewModel.text.observe(viewLifecycleOwner) {
			textView.text = it
		}
	}
}