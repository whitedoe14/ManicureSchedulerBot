package com.anastasiia.manicureschedulerbot.infrastructure.database.manicurist.repository

import com.anastasiia.manicureschedulerbot.infrastructure.database.manicurist.entity.ManicuristEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ManicuristRepository : JpaRepository<ManicuristEntity, UUID>
