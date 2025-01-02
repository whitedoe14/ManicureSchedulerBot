package com.anastasiia.manicureschedulerbot.infrastructure.database.schedule.mapper

import com.anastasiia.manicureschedulerbot.domain.schedule.model.Schedule
import com.anastasiia.manicureschedulerbot.domain.schedule.valueobject.ScheduleId
import com.anastasiia.manicureschedulerbot.infrastructure.database.schedule.entity.ScheduleEntity

object ScheduleMapper {
    fun ScheduleEntity.toModel(): Schedule {
        return Schedule(
            scheduleId = ScheduleId(id),
            restDaysOfWeek = restDaysOfWeek,
            scheduleType = scheduleType,
            startWorkingTime = startWorkingTime,
            endWorkingTime = endWorkingTime,
        )
    }
}
