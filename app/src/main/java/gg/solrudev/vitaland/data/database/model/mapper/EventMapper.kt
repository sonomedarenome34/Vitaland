package gg.solrudev.vitaland.data.database.model.mapper

import gg.solrudev.vitaland.data.database.model.EventModel
import gg.solrudev.vitaland.data.mapper.Mapper
import gg.solrudev.vitaland.domain.model.Event
import javax.inject.Inject

interface EventMapper : Mapper<EventModel, Event>

class EventMapperImpl @Inject constructor() : EventMapper {
	override fun invoke(eventModel: EventModel) = Event(eventModel.id, eventModel.name, eventModel.date)
}