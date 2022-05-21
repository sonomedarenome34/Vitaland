package gg.solrudev.vitaland.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity(tableName = "parents_children", primaryKeys = ["parent_id", "child_id"])
data class ParentChildrenCrossRef(
	@ColumnInfo(name = "parent_id") val parentId: Int,
	@ColumnInfo(name = "child_id") val childId: Int
)
