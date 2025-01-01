package com.anastasiia.manicureschedulerbot.infrastructure.database.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "clients")
class ClientEntity(
    @Id
    var id: UUID,
    @Column(name = "telegram_id", unique = true, nullable = false)
    var telegramId: Long,
    @Column(name = "full_name", nullable = false)
    var fullName: String,
    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String,
    @Column(name = "manicure_type", nullable = false)
    var manicureType: String,
    @OneToMany(
        mappedBy = "client",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    var events: MutableList<EventEntity>? = null,
)
