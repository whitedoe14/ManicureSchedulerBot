package com.anastasiia.manicureschedulerbot.domain.schedule.repository

import com.anastasiia.manicureschedulerbot.domain.manicurist.valueobject.ManicuristId
import com.anastasiia.manicureschedulerbot.domain.schedule.model.Schedule

interface IScheduleRepository {
    fun getScheduleByManicuristIdForToday(manicuristId: ManicuristId): Schedule
}
