package com.anastasiia.manicureschedulerbot.domain.schedule.valueobject

import com.anastasiia.manicureschedulerbot.domain.shared.ValueObject
import java.util.UUID

@JvmInline
value class ScheduleId(val value: UUID) : ValueObject {
    companion object {
        fun generate(): UUID {
            return UUID.randomUUID()
        }
    }
}
