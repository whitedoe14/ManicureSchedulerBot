package com.anastasiia.manicureschedulerbot.infrastructure.database.manicurist.entity

import com.anastasiia.manicureschedulerbot.infrastructure.database.event.entity.EventEntity
import com.anastasiia.manicureschedulerbot.infrastructure.database.schedule.entity.ScheduleEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "manicurists")
class ManicuristEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,
    @Column(name = "full_name", nullable = false)
    var fullName: String,
    @Column(name = "portfolio_link")
    var portfolioLink: String? = null,
    @ManyToMany
    var schedules: List<ScheduleEntity> = emptyList(),
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var events: Set<EventEntity> = emptySet(),
)
