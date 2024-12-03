package com.anastasiia.manicureschedulerbot.domain.client.model

import com.anastasiia.manicureschedulerbot.domain.client.valueobject.ClientId
import com.anastasiia.manicureschedulerbot.domain.client.valueobject.FullName
import com.anastasiia.manicureschedulerbot.domain.client.valueobject.ManicureType
import com.anastasiia.manicureschedulerbot.domain.client.valueobject.PhoneNumber
import com.anastasiia.manicureschedulerbot.domain.client.valueobject.TelegramId
import com.anastasiia.manicureschedulerbot.domain.event.model.Event

data class Client(
    val clientId: ClientId?,
    val telegramId: TelegramId?,
    val fullName: FullName,
    val phoneNumber: PhoneNumber,
    val manicureType: ManicureType,
    val events: List<Event>,
    )
