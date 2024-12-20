package com.anastasiia.manicureschedulerbot.handler.update.impl

import com.anastasiia.manicureschedulerbot.exception.custom.NoSuchHandlerException
import com.anastasiia.manicureschedulerbot.handler.message.MessageHandler
import com.anastasiia.manicureschedulerbot.handler.update.UpdateHandler
import com.anastasiia.manicureschedulerbot.model.UserMessageRequest
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
        val handler = findHandler(userRequest) ?: return
        handler.handle(userRequest)
    }

    private fun findHandler(userRequest: UserMessageRequest): MessageHandler? {
        val handlers =
            this.handlers.stream()
                .filter { it.isApplicable(userRequest) }
                .toList()
        if (handlers.size > 1) {
            throw NoSuchHandlerException("Found more than one message handler: ${handlers.size}")
        }
        if (handlers.isEmpty()) {
            return null
        }
        return handlers[0]
    }
}
