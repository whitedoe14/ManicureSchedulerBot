package com.anastasiia.manicureschedulerbot.infrastructure.database.client.repository

import com.anastasiia.manicureschedulerbot.infrastructure.database.client.entity.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ClientRepository : JpaRepository<ClientEntity, UUID>
