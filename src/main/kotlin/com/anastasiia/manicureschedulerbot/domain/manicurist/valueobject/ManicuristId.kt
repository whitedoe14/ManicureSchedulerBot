package com.anastasiia.manicureschedulerbot.domain.manicurist.valueobject

import com.anastasiia.manicureschedulerbot.domain.shared.ValueObject
import java.util.UUID

@JvmInline
value class ManicuristId(private val id: UUID) : ValueObject {
    companion object {
        fun generate(): UUID {
            return UUID.randomUUID()
        }
    }
}
