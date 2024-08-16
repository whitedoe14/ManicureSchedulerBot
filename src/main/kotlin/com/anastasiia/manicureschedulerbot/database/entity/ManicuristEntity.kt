package com.anastasiia.manicureschedulerbot.database.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "manicurists")
class ManicuristEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null

    @Column(name = "full_name", nullable = false)
    var fullName: String

    @Column(name = "portfolio_link")
    var portfolioLink: String? = null

    @ManyToMany
    var schedules: List<ScheduleEntity>? = emptyList()

    @OneToMany(mappedBy = "manicurist", cascade = [CascadeType.ALL])
    var events: Set<EventEntity>? = emptySet()

    constructor(fullName: String) {
        this.fullName = fullName
    }

}