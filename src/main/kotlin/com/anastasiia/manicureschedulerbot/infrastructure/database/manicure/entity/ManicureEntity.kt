package com.anastasiia.manicureschedulerbot.infrastructure.database.manicure.entity

import com.anastasiia.manicureschedulerbot.domain.manicure.valueobject.Currency
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "manicures")
class ManicureEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "price", nullable = false)
    var price: Int,
    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    var currency: Currency,
    @Column(name = "duration_in_min", nullable = false)
    var durationInMin: Int,
)
