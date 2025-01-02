package com.anastasiia.manicureschedulerbot.infrastructure.database.schedule.repository

import com.anastasiia.manicureschedulerbot.domain.manicurist.valueobject.ManicuristId
import com.anastasiia.manicureschedulerbot.domain.schedule.model.Schedule
import com.anastasiia.manicureschedulerbot.domain.schedule.repository.IScheduleRepository
import com.anastasiia.manicureschedulerbot.infrastructure.database.schedule.mapper.ScheduleMapper.toModel
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class ScheduleRepository(
    private val scheduleJpaRepository: ScheduleJpaRepository,
) : IScheduleRepository {
    override fun getScheduleByManicuristIdForToday(manicuristId: ManicuristId): Schedule {
        val today = LocalDate.now().dayOfWeek
        return scheduleJpaRepository.getScheduleByManicuristIdForToday(manicuristId.value, today)
            .toModel()
    }
}
