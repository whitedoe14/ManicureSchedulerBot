package com.anastasiia.manicureschedulerbot.handler.update.impl

import com.anastasiia.manicureschedulerbot.exception.custom.NoSuchHandlerException
import com.anastasiia.manicureschedulerbot.handler.callbackquery.CallbackQueryHandler
import com.anastasiia.manicureschedulerbot.handler.update.UpdateHandler
import com.anastasiia.manicureschedulerbot.model.UserCallbackQueryRequest
import com.anastasiia.manicureschedulerbot.service.UserRequestService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class CallbackQueryUpdateHandler(
    private val handlers: List<CallbackQueryHandler>,
    private val userRequestService: UserRequestService,
) : UpdateHandler {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun isApplicable(update: Update): Boolean {
        return update.hasCallbackQuery()
    }

    override fun handle(update: Update) {
        val callbackQuery = update.callbackQuery
        val userId = callbackQuery.from.id
        val userRequest = userRequestService.createUserCallbackQueryRequest(userId, callbackQuery)
        logger.info("Received callbackQuery:${callbackQuery.id} update from user:$userId")
        val handler = findHandler(userRequest) ?: return
        handler.handle(userRequest)
        logger.info("CallbackQuery:${callbackQuery.id} update was processed")
    }

    private fun findHandler(userRequest: UserCallbackQueryRequest): CallbackQueryHandler? {
        val handlers =
            this.handlers.stream()
                .filter { it.isApplicable(userRequest) }
                .toList()
        if (handlers.size > 1) {
            throw NoSuchHandlerException("Found more than one callbackQuery handler: ${handlers.size}")
        }
        if (handlers.isEmpty()) {
            return null
        }
        return handlers[0]
    }
}
