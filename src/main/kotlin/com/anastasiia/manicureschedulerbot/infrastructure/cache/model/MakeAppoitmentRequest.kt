package com.anastasiia.manicureschedulerbot.infrastructure.cache.model

import java.time.LocalDateTime

data class MakeAppoitmentRequest(
    val userId: Long,
    val manicuristId: Long,
    val timeFrom: LocalDateTime,
    val timeTo: LocalDateTime,
)
