package com.anastasiia.manicureschedulerbot.mapper.request

import com.anastasiia.manicureschedulerbot.database.entity.EventEntity
import com.anastasiia.manicureschedulerbot.dto.BookTimeDto
import com.anastasiia.manicureschedulerbot.mapper.BaseMapper
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface BookTimeReqMapper : BaseMapper<EventEntity, BookTimeDto>
