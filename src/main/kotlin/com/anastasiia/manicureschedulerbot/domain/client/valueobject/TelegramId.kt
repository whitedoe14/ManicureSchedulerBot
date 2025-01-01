package com.anastasiia.manicureschedulerbot.domain.client.valueobject

import com.anastasiia.manicureschedulerbot.domain.shared.ValueObject

@JvmInline
value class TelegramId(private val telegramId: Long) : ValueObject
