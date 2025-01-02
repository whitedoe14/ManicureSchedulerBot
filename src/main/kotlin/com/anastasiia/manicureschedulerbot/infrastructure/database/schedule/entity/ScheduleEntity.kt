package com.anastasiia.manicureschedulerbot.infrastructure.database.schedule.entity

import com.anastasiia.manicureschedulerbot.domain.schedule.valueobject.ScheduleType
import com.anastasiia.manicureschedulerbot.infrastructure.database.manicurist.entity.ManicuristEntity
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table
import java.time.DayOfWeek
import java.time.LocalTime
import java.util.EnumSet
import java.util.UUID

@Entity
@Table(name = "schedules")
class ScheduleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,
    @ElementCollection(targetClass = DayOfWeek::class)
    @CollectionTable(name = "schedule_weekends", joinColumns = [JoinColumn(name = "schedule_id")])
    @Column(name = "weekend_day")
    @Enumerated(STRING)
    var restDaysOfWeek: Set<DayOfWeek> = EnumSet.noneOf(DayOfWeek::class.java),
    @Enumerated(STRING)
    @Column(name = "schedule_types")
    var scheduleType: ScheduleType,
    @Column(name = "start_working_time")
    var startWorkingTime: LocalTime,
    @Column(name = "end_working_time")
    var endWorkingTime: LocalTime,
    @ManyToMany
    @JoinTable(
        name = "schedule_manicurist",
        joinColumns = [JoinColumn(name = "schedule_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "manicurist_id", referencedColumnName = "id")],
    )
    var manicurists: List<ManicuristEntity> = emptyList(),
)
