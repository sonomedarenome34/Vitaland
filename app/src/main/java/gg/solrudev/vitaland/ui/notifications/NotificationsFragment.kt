package gg.solrudev.vitaland.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import gg.solrudev.vitaland.R
import gg.solrudev.vitaland.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

	private val binding by viewBinding(FragmentNotificationsBinding::bind)
	private val notificationsViewModel by viewModels<NotificationsViewModel>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		val textView = binding.textNotifications
		notificationsViewModel.text.observe(viewLifecycleOwner) {
			textView.text = it
		}
	}
}