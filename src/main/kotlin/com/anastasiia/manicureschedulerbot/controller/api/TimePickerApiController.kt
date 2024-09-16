package com.anastasiia.manicureschedulerbot.controller.api

import com.anastasiia.manicureschedulerbot.dto.BookTimeDto
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TimePickerApiController {

    val logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping("/submit-book")
    fun handleSubmit(@RequestBody bookTimeDto: BookTimeDto): ResponseEntity<HttpStatus> {
        logger.info("received book dto: $bookTimeDto")

        return ResponseEntity.ok().build()
    }
}
