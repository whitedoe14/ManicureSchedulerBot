package com.anastasiia.manicureschedulerbot.domain.event.model

import com.anastasiia.manicureschedulerbot.domain.client.model.Client
import com.anastasiia.manicureschedulerbot.domain.event.valueobject.EventId
import com.anastasiia.manicureschedulerbot.domain.event.valueobject.TimeFrom
import com.anastasiia.manicureschedulerbot.domain.event.valueobject.TimeTo

data class Event (
    val eventId: EventId?,
    val timeFrom: TimeFrom?,
    val timeTo: TimeTo?,
    val client: Client,
    )
