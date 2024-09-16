package com.anastasiia.manicureschedulerbot.dto

import java.time.LocalDateTime

data class BookTimeDto(
    var telegramUserId: Long?,
    var dateTime: LocalDateTime?,
)
