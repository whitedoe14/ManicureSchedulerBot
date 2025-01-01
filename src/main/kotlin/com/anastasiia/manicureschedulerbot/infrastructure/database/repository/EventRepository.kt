package com.anastasiia.manicureschedulerbot.infrastructure.database.repository

import com.anastasiia.manicureschedulerbot.infrastructure.database.entity.EventEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<EventEntity, Long>
