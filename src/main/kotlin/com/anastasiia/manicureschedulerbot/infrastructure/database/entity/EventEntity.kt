package com.anastasiia.manicureschedulerbot.infrastructure.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "events")
class EventEntity(
    @Id
    var id: UUID,
    @Column(name = "time_from")
    var timeFrom: LocalDateTime,
    @Column(name = "time_to")
    var timeTo: LocalDateTime,
    @ManyToOne
    @JoinColumn(
        name = "manicurist",
        referencedColumnName = "id",
    )
    var manicurist: ManicuristEntity,
    @ManyToOne(optional = false)
    @JoinColumn(
        name = "client",
        referencedColumnName = "telegram_id",
        nullable = false,
    )
    var client: ClientEntity,
)
