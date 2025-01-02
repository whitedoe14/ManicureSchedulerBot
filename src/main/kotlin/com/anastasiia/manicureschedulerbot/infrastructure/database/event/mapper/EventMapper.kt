package com.anastasiia.manicureschedulerbot.infrastructure.database.event.mapper

import com.anastasiia.manicureschedulerbot.domain.event.model.Event
import com.anastasiia.manicureschedulerbot.domain.event.valueobject.EventId
import com.anastasiia.manicureschedulerbot.infrastructure.database.event.entity.EventEntity

object EventMapper {
    fun EventEntity.toModel(): Event {
        return Event(
            eventId = EventId(this.id),
            timeFrom = this.timeFrom,
            timeTo = this.timeTo,
            durationInMin = this.durationInMin,
        )
    }
}
