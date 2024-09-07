package com.anastasiia.manicureschedulerbot.database.repository

import com.anastasiia.manicureschedulerbot.database.entity.ManicureEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ManicureRepository : JpaRepository<ManicureEntity, Long>
