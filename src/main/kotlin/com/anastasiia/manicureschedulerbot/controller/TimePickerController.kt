package com.anastasiia.manicureschedulerbot.controller

import com.anastasiia.manicureschedulerbot.database.repository.ClientRepository
import com.anastasiia.manicureschedulerbot.database.repository.ManicuristRepository
import com.anastasiia.manicureschedulerbot.dto.BookTimeDto
import com.anastasiia.manicureschedulerbot.service.EventService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class TimePickerController(
    private val eventService: EventService,
    private val clientRepository: ClientRepository,
    private val manicuristRepository: ManicuristRepository,
    @Value("\${telegram.time-picker-url}") private val callbackUrl: String,
) {

    val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun getBookingPage(model: Model): String {
        model.addAttribute("callbackUrl", callbackUrl)
        return "index"
    }

    @PostMapping
    fun handleSubmit(@RequestBody bookTimeDto: BookTimeDto): ResponseEntity<HttpStatus> {
        logger.info("received book dto: $bookTimeDto")

        return ResponseEntity.ok().build()
   }
}
