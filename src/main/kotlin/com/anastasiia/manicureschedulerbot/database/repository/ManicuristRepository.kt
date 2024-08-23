package com.anastasiia.manicureschedulerbot.database.repository

import com.anastasiia.manicureschedulerbot.database.entity.ManicuristEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ManicuristRepository : JpaRepository<ManicuristEntity, Long>
