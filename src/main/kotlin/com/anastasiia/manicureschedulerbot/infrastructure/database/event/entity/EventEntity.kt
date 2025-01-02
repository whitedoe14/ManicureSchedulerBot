package com.anastasiia.manicureschedulerbot.infrastructure.database.event.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "events")
class EventEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID,
    @Column(name = "time_from")
    var timeFrom: LocalDateTime,
    @Column(name = "time_to")
    var timeTo: LocalDateTime,
    @Column(name = "duration")
    var durationInMin: Int,
)
