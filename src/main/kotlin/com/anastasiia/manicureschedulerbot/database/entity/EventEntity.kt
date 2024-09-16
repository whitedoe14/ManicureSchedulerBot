package com.anastasiia.manicureschedulerbot.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "events")
class EventEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null

    @Column(name = "time_from")
    var timeFrom: LocalDateTime? = null

    @Column(name = "time_to")
    var timeTo: LocalDateTime? = null

    @ManyToOne
    @JoinColumn(
        name = "manicurist",
        referencedColumnName = "id",
    )
    var manicurist: ManicuristEntity

    @ManyToOne(optional = false)
    @JoinColumn(
        name = "client",
        referencedColumnName = "telegram_id",
        nullable = false,
    )
    var client: ClientEntity

    constructor(client: ClientEntity, manicurist: ManicuristEntity) {
        this.client = client
        this.manicurist = manicurist
    }
}
