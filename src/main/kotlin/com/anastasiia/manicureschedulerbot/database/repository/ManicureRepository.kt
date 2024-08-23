package com.anastasiia.manicureschedulerbot.database.repository

import com.anastasiia.manicureschedulerbot.database.entity.ManicureEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ManicureRepository : JpaRepository<ManicureEntity, Long>
