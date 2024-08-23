package com.anastasiia.manicureschedulerbot.service

import com.anastasiia.manicureschedulerbot.model.UserCallbackQueryRequest
import com.anastasiia.manicureschedulerbot.model.UserMessageRequest
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.CallbackQuery
import org.telegram.telegrambots.meta.api.objects.Message

@Service
class UserRequestService(
    private val userStateService: UserStateService,
) {
    fun createUserMessageRequest(
        userId: Long,
        message: Message,
    ): UserMessageRequest {
        val userSession = userStateService.getUserState(userId)
        return UserMessageRequest(userId, message, userSession)
    }

    fun createUserCallbackQueryRequest(
        userId: Long,
        callbackQuery: CallbackQuery,
    ): UserCallbackQueryRequest {
        val userSession = userStateService.getUserState(userId)
        return UserCallbackQueryRequest(userId, callbackQuery, userSession)
    }
}
