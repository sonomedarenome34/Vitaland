package gg.solrudev.vitaland.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class ShiftWithEvents(
	@Embedded val shift: ShiftModel,
	@Relation(
		parentColumn = "id",
		entityColumn = "shift_id"
	)
	val events: List<EventModel>?
)
