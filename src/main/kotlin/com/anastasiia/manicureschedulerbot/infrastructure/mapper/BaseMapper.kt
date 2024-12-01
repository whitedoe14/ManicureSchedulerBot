package com.anastasiia.manicureschedulerbot.infrastructure.mapper

interface BaseMapper<E, D> {
    fun toEntity(dto: D): E

    fun toDto(entity: E): D
}
