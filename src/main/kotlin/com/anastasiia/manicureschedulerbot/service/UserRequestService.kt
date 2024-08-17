package com.anastasiia.manicureschedulerbot.service

import com.anastasiia.manicureschedulerbot.model.UserMessageRequest
import com.anastasiia.manicureschedulerbot.session.service.UserSessionService
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class UserRequestService(
    private val userSessionService: UserSessionService
) {
    fun createUserMessageRequest(userId: Long, message: Message): UserMessageRequest {
        val userState = userSessionService.getUserSession(userId)
        return UserMessageRequest(userId, message, userState)
    }
}