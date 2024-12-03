package com.anastasiia.manicureschedulerbot.domain.manicure.valueobject

import com.anastasiia.manicureschedulerbot.domain.common.ValueObject
import java.util.UUID

@JvmInline
value class ManicureId (private val manicureId: UUID) : ValueObject {
    companion object {
        fun generate(): UUID {
            return UUID.randomUUID()
        }
    }
}
