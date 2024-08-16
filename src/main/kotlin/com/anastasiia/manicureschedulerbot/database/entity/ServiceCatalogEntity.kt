package com.anastasiia.manicureschedulerbot.database.entity

import com.anastasiia.manicureschedulerbot.database.vo.CurrencyVo
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "service_catalog")
class ServiceCatalogEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null

    @Column(name = "name", nullable = false)
    var name: String

    @Column(name = "price", nullable = false)
    var price: Int

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    var currency: CurrencyVo

    @Column(name = "duration_in_min", nullable = false)
    var durationInMin: Int

    constructor(name: String, price: Int, durationInMin: Int, currency: CurrencyVo) {
        this.name = name
        this.price = price
        this.durationInMin = durationInMin
        this.currency = currency
    }
}