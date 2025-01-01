package com.anastasiia.manicureschedulerbot.domain.client.valueobject

import com.anastasiia.manicureschedulerbot.domain.shared.ValueObject
import java.util.UUID

@JvmInline
value class ClientId(private val id: UUID) : ValueObject {
    companion object {
        fun generate(): UUID {
            return UUID.randomUUID()
        }
    }
}
