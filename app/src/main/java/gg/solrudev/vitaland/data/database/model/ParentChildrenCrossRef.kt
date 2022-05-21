package gg.solrudev.vitaland.data.database.model

import androidx.room.*

@Entity(
	tableName = "parents_children",
	indices = [Index("child_id")],
	primaryKeys = ["parent_id", "child_id"],
	foreignKeys = [
		ForeignKey(
			entity = UserModel::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("parent_id")
		),
		ForeignKey(
			entity = ShiftModel::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("child_id")
		)
	]
)
data class ParentChildrenCrossRef(
	@ColumnInfo(name = "parent_id") val parentId: Int,
	@ColumnInfo(name = "child_id") val childId: Int
)

data class ParentWithChildren(
	@Embedded val parent: UserModel,
	@Relation(
		parentColumn = "id",
		entityColumn = "id",
		associateBy = Junction(
			ParentChildrenCrossRef::class,
			parentColumn = "parent_id",
			entityColumn = "child_id"
		)
	)
	val children: List<UserModel>?
)