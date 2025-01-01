package com.anastasiia.manicureschedulerbot.infrastructure.database.repository

import com.anastasiia.manicureschedulerbot.infrastructure.database.entity.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<ClientEntity, Long>
