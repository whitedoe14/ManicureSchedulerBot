package com.anastasiia.manicureschedulerbot.infrastructure.controller.dto

import java.time.LocalDateTime

data class BookTimeDto(
    var telegramUserId: Long?,
    var dateTime: LocalDateTime?,
)
