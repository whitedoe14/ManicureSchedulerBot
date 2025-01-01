package com.anastasiia.manicureschedulerbot.infrastructure.database.repository

import com.anastasiia.manicureschedulerbot.infrastructure.database.entity.ManicureEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ManicureRepository : JpaRepository<ManicureEntity, Long>
