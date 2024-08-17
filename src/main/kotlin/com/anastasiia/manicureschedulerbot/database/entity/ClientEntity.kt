package com.anastasiia.manicureschedulerbot.database.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "clients")
class ClientEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null

    @Column(name = "telegram_id", unique = true, nullable = false)
    var telegramId: Long

    @Column(name = "full_name", nullable = false)
    var fullName: String

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String

    @Column(name = "manicure_type", nullable = false)
    var manicureType: String

    @OneToMany(
        mappedBy = "client",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var events: MutableList<EventEntity>? = null

    constructor(
        telegramId: Long,
        fullName: String,
        phoneNumber: String,
        manicureType: String
    ) {
        this.telegramId = telegramId
        this.fullName = fullName
        this.phoneNumber = phoneNumber
        this.manicureType = manicureType
    }
}
