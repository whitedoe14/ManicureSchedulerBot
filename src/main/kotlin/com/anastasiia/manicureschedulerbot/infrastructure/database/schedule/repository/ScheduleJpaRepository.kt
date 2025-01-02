package com.anastasiia.manicureschedulerbot.infrastructure.database.schedule.repository

import com.anastasiia.manicureschedulerbot.infrastructure.database.schedule.entity.ScheduleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.DayOfWeek
import java.util.UUID

interface ScheduleJpaRepository : JpaRepository<ScheduleEntity, UUID> {
    @Query(
        """
        SELECT s
        FROM ScheduleEntity s
        JOIN s.manicurists m
        WHERE m.id = :manicuristId
          AND :today NOT IN (SELECT rd FROM s.restDaysOfWeek rd)
    """,
    )
    fun getScheduleByManicuristIdForToday(
        @Param("manicuristId") manicuristId: UUID,
        @Param("today") today: DayOfWeek,
    ): ScheduleEntity
}
