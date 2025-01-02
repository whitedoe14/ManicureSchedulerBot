package com.anastasiia.manicureschedulerbot.infrastructure.database.event.repository

import com.anastasiia.manicureschedulerbot.infrastructure.database.event.entity.EventEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface EventJpaRepository : JpaRepository<EventEntity, UUID> {
    @Query(
        nativeQuery = true,
        value = """
        SELECT e.*
        FROM events e
        JOIN manicurists_events me ON e.id = me.event_id
        WHERE me.manicurist_id = :manicuristId
        AND e.time_from = CURRENT_DATE
    """,
    )
    fun findTodayEventsByManicurist(
        @Param("manicuristId") manicuristId: UUID,
    ): List<EventEntity>
}
