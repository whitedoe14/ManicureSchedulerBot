package com.anastasiia.manicureschedulerbot.infrastructure.database.repository

import com.anastasiia.manicureschedulerbot.infrastructure.database.entity.ManicuristEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ManicuristRepository : JpaRepository<ManicuristEntity, Long>
