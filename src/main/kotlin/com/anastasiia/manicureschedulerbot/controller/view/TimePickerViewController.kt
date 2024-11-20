package com.anastasiia.manicureschedulerbot.controller.view

import com.anastasiia.manicureschedulerbot.database.repository.ClientRepository
import com.anastasiia.manicureschedulerbot.database.repository.ManicuristRepository
import com.anastasiia.manicureschedulerbot.service.EventService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class TimePickerViewController(
    private val eventService: EventService,
    private val clientRepository: ClientRepository,
    private val manicuristRepository: ManicuristRepository,
    @Value("\${telegram.time-picker-url}") private val callbackUrl: String,
) {

    val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/book")
    fun getBookingPage(model: Model): String {
        model.addAttribute("callbackUrl", callbackUrl)
        return "simple-calendar"
//        return "index" // TODO: change it
    }
}
