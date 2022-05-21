package gg.solrudev.vitaland.data.database.model.mapper

import gg.solrudev.vitaland.data.database.model.ShiftModel
import gg.solrudev.vitaland.data.mapper.Mapper
import gg.solrudev.vitaland.domain.model.Shift
import javax.inject.Inject

interface ShiftMapper : Mapper<ShiftModel, Shift>

class ShiftMapperImpl @Inject constructor() : Mapper<ShiftModel, Shift> {

	override fun invoke(shiftModel: ShiftModel): Shift {
		return Shift(shiftModel.id, shiftModel.name, shiftModel.startDate, shiftModel.endDate)
	}
}