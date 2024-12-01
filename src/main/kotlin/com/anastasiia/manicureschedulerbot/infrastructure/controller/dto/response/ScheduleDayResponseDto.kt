package com.anastasiia.manicureschedulerbot.infrastructure.controller.dto.response

import java.time.LocalDate

data class ScheduleDayResponseDto(
    val date: LocalDate,
    val isAvailable: Boolean,
)
