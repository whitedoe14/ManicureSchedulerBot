package com.anastasiia.manicureschedulerbot.handler.update.impl

import com.anastasiia.manicureschedulerbot.handler.message.MessageHandler
import com.anastasiia.manicureschedulerbot.handler.update.UpdateHandler
import com.anastasiia.manicureschedulerbot.service.UserRequestService
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update


@Component
class MessageUpdateHandler(
    private val handlers: List<MessageHandler>,
    private val userRequestService: UserRequestService,
) : UpdateHandler {

    override fun isApplicable(update: Update): Boolean {
        return update.hasMessage()
    }

    override fun handle(update: Update) {
        val message = update.message
        val userId = message.from.id
        val userRequest = userRequestService.createUserMessageRequest(userId, message)
        for (handler in handlers) {
            if (handler.isApplicable(userRequest)) {
                handler.handle(userRequest)
            }
        }
    }
}