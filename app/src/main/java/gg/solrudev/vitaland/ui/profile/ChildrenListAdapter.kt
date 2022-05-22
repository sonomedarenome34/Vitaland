package gg.solrudev.vitaland.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import gg.solrudev.vitaland.MobileNavigationDirections
import gg.solrudev.vitaland.R
import gg.solrudev.vitaland.databinding.ChildItemBinding
import gg.solrudev.vitaland.domain.model.ChildWithShift

class ChildrenListAdapter : ListAdapter<ChildWithShift, ChildrenListAdapter.ChildViewHolder>(ChildrenDiffCallback) {

	class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		private val binding = ChildItemBinding.bind(itemView)

		fun bind(data: ChildWithShift) {
			with(data.child) {
				binding.textViewChildName.text =
					itemView.context.getString(R.string.profile_displayed_name, name.firstName, name.lastName)
			}
			binding.textViewShiftName.text = data.shift.name
			binding.buttonProfileEdit.setOnClickListener {
				val toChildProfile = MobileNavigationDirections.actionGlobalProfile(data.child)
				binding.root.findNavController().navigate(toChildProfile)
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.child_item, parent, false)
		return ChildViewHolder(view)
	}

	override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
		val appData = getItem(position)
		holder.bind(appData)
	}
}

object ChildrenDiffCallback : DiffUtil.ItemCallback<ChildWithShift>() {

	override fun areItemsTheSame(oldItem: ChildWithShift, newItem: ChildWithShift) =
		oldItem.child.id == newItem.child.id

	override fun areContentsTheSame(oldItem: ChildWithShift, newItem: ChildWithShift) = oldItem == newItem
}