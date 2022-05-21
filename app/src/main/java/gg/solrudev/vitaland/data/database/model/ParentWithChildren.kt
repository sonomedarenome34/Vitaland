package gg.solrudev.vitaland.data.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class ParentWithChildren(
	@Embedded val parent: UserModel,
	@Relation(
		parentColumn = "id",
		entityColumn = "id"
	)
	val children: List<UserModel>
)