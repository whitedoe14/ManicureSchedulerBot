package com.anastasiia.manicureschedulerbot.dto

import java.time.LocalDateTime

data class BookTimeDto(
    var telegramId: Long?,
    var dateTime: LocalDateTime?
)