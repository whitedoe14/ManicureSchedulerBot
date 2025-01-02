package com.anastasiia.manicureschedulerbot.domain.event.repository

import com.anastasiia.manicureschedulerbot.domain.event.model.Event
import com.anastasiia.manicureschedulerbot.domain.manicurist.valueobject.ManicuristId
import com.anastasiia.manicureschedulerbot.infrastructure.database.event.entity.EventEntity

interface IEventRepository {
    fun save(eventEntity: EventEntity): EventEntity

    fun getEventsForCurrentDayByManicuristId(manicuristId: ManicuristId): List<Event>
}
