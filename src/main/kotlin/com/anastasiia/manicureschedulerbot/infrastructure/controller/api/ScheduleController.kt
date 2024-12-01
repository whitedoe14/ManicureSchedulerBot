package com.anastasiia.manicureschedulerbot.infrastructure.controller.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/schedule")
@RestController
class ScheduleController {

    @GetMapping
    fun getAvailableDays(): ResponseEntity<String> {

    }

}
