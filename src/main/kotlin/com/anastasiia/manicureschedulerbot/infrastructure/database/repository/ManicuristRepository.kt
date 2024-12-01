package com.anastasiia.manicureschedulerbot.infrastructure.database.repository

import com.anastasiia.manicureschedulerbot.infrastructure.database.entity.ManicuristEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface ManicuristRepository : JpaRepository<ManicuristEntity, Long>
