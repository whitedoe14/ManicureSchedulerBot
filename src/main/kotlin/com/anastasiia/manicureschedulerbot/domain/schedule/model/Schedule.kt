package com.anastasiia.manicureschedulerbot.domain.schedule.model

import com.anastasiia.manicureschedulerbot.domain.manicurist.model.Manicurist
import com.anastasiia.manicureschedulerbot.domain.schedule.valueobject.ScheduleId
import com.anastasiia.manicureschedulerbot.domain.schedule.valueobject.ScheduleType
import java.time.DayOfWeek
import java.time.LocalTime
import java.util.EnumSet

data class Schedule(
    val scheduleId: ScheduleId,
    var restDaysOfWeek: Set<DayOfWeek> = EnumSet.noneOf(DayOfWeek::class.java),
    var scheduleType: ScheduleType,
    var startWorkingTime: LocalTime,
    var endWorkingTime: LocalTime,
    var manicurists: List<Manicurist> = emptyList(),
)
