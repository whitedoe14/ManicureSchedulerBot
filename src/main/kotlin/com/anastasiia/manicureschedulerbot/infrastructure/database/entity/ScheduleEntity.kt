package com.anastasiia.manicureschedulerbot.infrastructure.database.entity

import com.anastasiia.manicureschedulerbot.domain.schedule.valueobject.ScheduleType
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
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
    val id: UUID,
    @ElementCollection(targetClass = DayOfWeek::class)
    @CollectionTable(name = "schedule_weekends", joinColumns = [JoinColumn(name = "schedule_id")])
    @Column(name = "weekend_day")
    @Enumerated(EnumType.STRING)
    var restDaysOfWeek: Set<DayOfWeek> = EnumSet.noneOf(DayOfWeek::class.java),
    @Enumerated(EnumType.STRING)
    @Column(name = "schedule_types")
    var scheduleType: ScheduleType,
    @Column(name = "start_working_time", nullable = false)
    var startWorkingTime: LocalTime,
    @Column(name = "end_working_time", nullable = false)
    var endWorkingTime: LocalTime,
    @ManyToMany
    @JoinTable(
        name = "schedule_manicurist",
        joinColumns = [JoinColumn(name = "schedule_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "manicurist_id", referencedColumnName = "id")],
    )
    var manicurists: List<ManicuristEntity> = emptyList(),
)
