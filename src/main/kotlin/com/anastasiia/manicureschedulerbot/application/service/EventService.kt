package com.anastasiia.manicureschedulerbot.application.service

import com.anastasiia.manicureschedulerbot.infrastructure.database.entity.EventEntity
import com.anastasiia.manicureschedulerbot.infrastructure.database.repository.EventRepository
import org.springframework.stereotype.Service

@Service
class EventService(
    val eventRepository: EventRepository,
) {
    fun saveEvent(event: EventEntity): EventEntity {
        return eventRepository.save(event)
    }
}
