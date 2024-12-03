package com.anastasiia.manicureschedulerbot.domain.event.valueobject

import com.anastasiia.manicureschedulerbot.domain.common.ValueObject
import java.util.UUID

@JvmInline
value class EventId (private val id: UUID) : ValueObject {
    companion object {
        fun generate(): UUID {
            return UUID.randomUUID()
        }
    }
}
