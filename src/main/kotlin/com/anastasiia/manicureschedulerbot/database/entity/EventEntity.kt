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

    @Column(name = "from")
    var from: LocalDateTime? = null

    @Column(name = "to")
    var to: LocalDateTime? = null

    @ManyToOne
    @JoinColumn(
        name = "client",
        referencedColumnName = "telegramId",
    )
    var client: ClientEntity

    @ManyToOne
    @JoinColumn(
        name = "manicurist",
        referencedColumnName = "id"
    )
    var manicurist: ManicuristEntity

    constructor(client: ClientEntity, manicurist: ManicuristEntity) {
        this.client = client
        this.manicurist = manicurist
    }
}
