package com.anastasiia.manicureschedulerbot.database.repository

import com.anastasiia.manicureschedulerbot.database.entity.ManicuristEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ManicuristRepository : JpaRepository<ManicuristEntity, Long>
