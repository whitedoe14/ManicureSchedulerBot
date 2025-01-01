package com.anastasiia.manicureschedulerbot.infrastructure.database.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "manicurists")
class ManicuristEntity(
    @Id
    val id: UUID,
    @Column(name = "full_name", nullable = false)
    var fullName: String,
    @Column(name = "portfolio_link")
    var portfolioLink: String? = null,
    @ManyToMany
    var schedules: List<ScheduleEntity> = emptyList(),
    @OneToMany(mappedBy = "manicurist", cascade = [CascadeType.ALL])
    var events: Set<EventEntity> = emptySet(),
)
