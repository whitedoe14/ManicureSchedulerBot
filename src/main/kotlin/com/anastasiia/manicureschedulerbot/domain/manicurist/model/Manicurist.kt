package com.anastasiia.manicureschedulerbot.domain.manicurist.model

import com.anastasiia.manicureschedulerbot.domain.event.model.Event
import com.anastasiia.manicureschedulerbot.domain.manicurist.valueobject.ManicuristId
import com.anastasiia.manicureschedulerbot.domain.shared.FullName
import com.anastasiia.manicureschedulerbot.domain.shared.Url

data class Manicurist(
    val manicuristId: ManicuristId?,
    val fullName: FullName,
    var schedules: List<Schedule> = emptyList(),
    var events: Set<Event> = emptySet(),
    val portfolioLink: Url?,
)
