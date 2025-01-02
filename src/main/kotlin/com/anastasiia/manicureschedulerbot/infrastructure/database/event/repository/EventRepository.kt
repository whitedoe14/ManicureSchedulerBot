package com.anastasiia.manicureschedulerbot.infrastructure.database.event.repository

import com.anastasiia.manicureschedulerbot.domain.event.model.Event
import com.anastasiia.manicureschedulerbot.domain.event.repository.IEventRepository
import com.anastasiia.manicureschedulerbot.domain.manicurist.valueobject.ManicuristId
import com.anastasiia.manicureschedulerbot.infrastructure.database.event.entity.EventEntity
import com.anastasiia.manicureschedulerbot.infrastructure.database.event.mapper.EventMapper.toModel
import org.springframework.stereotype.Repository

@Repository
class EventRepository(
    private val eventJpaRepository: EventJpaRepository,
) : IEventRepository {
    override fun save(eventEntity: EventEntity): EventEntity {
        return eventJpaRepository.save(eventEntity)
    }

    override fun getEventsForCurrentDayByManicuristId(manicuristId: ManicuristId): List<Event> {
        return eventJpaRepository.findTodayEventsByManicurist(manicuristId.value)
            .map { entity -> entity.toModel() }
    }
}
