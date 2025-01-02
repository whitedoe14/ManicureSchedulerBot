package com.anastasiia.manicureschedulerbot.domain.event.model

import com.anastasiia.manicureschedulerbot.domain.event.valueobject.EventId
import com.anastasiia.manicureschedulerbot.infrastructure.database.event.entity.EventEntity
import java.time.LocalDateTime

data class Event(
    var eventId: EventId?,
    val timeFrom: LocalDateTime,
    val timeTo: LocalDateTime,
    val durationInMin: Int,
) {
    fun toEntity(): EventEntity {
        return EventEntity(
            id = eventId!!.value,
            timeFrom = timeFrom,
            timeTo = timeTo,
            durationInMin = durationInMin,
        )
    }
}
