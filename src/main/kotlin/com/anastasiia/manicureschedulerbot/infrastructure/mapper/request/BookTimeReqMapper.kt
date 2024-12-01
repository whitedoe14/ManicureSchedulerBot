package com.anastasiia.manicureschedulerbot.infrastructure.mapper.request

import com.anastasiia.manicureschedulerbot.infrastructure.database.entity.EventEntity
import com.anastasiia.manicureschedulerbot.infrastructure.controller.dto.BookTimeDto
import com.anastasiia.manicureschedulerbot.infrastructure.mapper.BaseMapper
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BookTimeReqMapper : BaseMapper<EventEntity, BookTimeDto>
