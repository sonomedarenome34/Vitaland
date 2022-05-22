package gg.solrudev.vitaland.ui.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import gg.solrudev.vitaland.databinding.BottomSheetEditProfileBinding
import gg.solrudev.vitaland.domain.model.User
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EditProfileFragment : BottomSheetDialogFragment() {

	private var binding: BottomSheetEditProfileBinding? = null
	private val viewModel by viewModels<EditProfileViewModel>()
	private var currentUser: User? = null
	private val args by navArgs<EditProfileFragmentArgs>()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = BottomSheetEditProfileBinding.inflate(inflater)
		return binding?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		observeUiState()
		binding?.buttonProfileEdit?.setOnClickListener {
			viewModel.persistUpdatedUser()
		}
		addEditTextListeners()
		if (savedInstanceState == null) {
			viewModel.loadUser(args.user)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		binding = null
	}

	private fun observeUiState() {
		viewLifecycleOwner.lifecycleScope.launch {
			viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.collect {
					currentUser = it.user
					it.user?.let { user ->
						binding?.textFieldEditProfileFirstName?.editText?.setString(user.name.firstName)
						binding?.textFieldEditProfileLastName?.editText?.setString(user.name.lastName)
						binding?.textFieldEditProfilePatronymic?.editText?.setString(user.name.patronymic)
						binding?.textFieldEditProfileEmail?.editText?.setString(user.email)
						binding?.textFieldEditProfilePhone?.editText?.setString(user.phone)
					}
					binding?.progressBarEditProfileLoading?.isVisible = it.isLoading
					binding?.groupEditProfileFields?.isInvisible = it.isLoading
					if (it.isUpdated) {
						findNavController().popBackStack()
					}
				}
			}
		}
	}

	private fun addEditTextListeners() {
		binding?.textFieldEditProfileFirstName?.editText?.updateUser { text, user ->
			val personName = user.name.copy(firstName = text.toString())
			user.copy(name = personName)
		}
		binding?.textFieldEditProfileLastName?.editText?.updateUser { text, user ->
			val personName = user.name.copy(lastName = text.toString())
			user.copy(name = personName)
		}
		binding?.textFieldEditProfilePatronymic?.editText?.updateUser { text, user ->
			val personName = user.name.copy(patronymic = text.toString())
			user.copy(name = personName)
		}
		binding?.textFieldEditProfileEmail?.editText?.updateUser { text, user ->
			user.copy(email = text.toString())
		}
		binding?.textFieldEditProfilePhone?.editText?.updateUser { text, user ->
			user.copy(phone = text.toString())
		}
	}

	private fun EditText.setString(string: String) {
		setText(string)
		text?.let { text ->
			setSelection(text.length)
		}
	}

	private inline fun EditText.updateUser(crossinline transform: (CharSequence?, User) -> User) {
		addTextChangedListener(
			onTextChanged = { text, _, _, _ ->
				currentUser?.let {
					viewModel.updateCurrentUserState(transform(text, it))
				}
			}
		)
	}
}