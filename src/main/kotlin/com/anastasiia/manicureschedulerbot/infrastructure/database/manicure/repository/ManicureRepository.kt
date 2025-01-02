package com.anastasiia.manicureschedulerbot.infrastructure.database.manicure.repository

import com.anastasiia.manicureschedulerbot.infrastructure.database.manicure.entity.ManicureEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ManicureRepository : JpaRepository<ManicureEntity, UUID>
