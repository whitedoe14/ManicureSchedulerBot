package com.anastasiia.manicureschedulerbot.domain.event.valueobject

import com.anastasiia.manicureschedulerbot.domain.common.ValueObject
import java.time.LocalDateTime

@JvmInline
value class TimeTo (private val timeTo: LocalDateTime) : ValueObject {
}
