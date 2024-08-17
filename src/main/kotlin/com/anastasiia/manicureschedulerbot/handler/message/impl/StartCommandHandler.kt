package com.anastasiia.manicureschedulerbot.handler.message.impl

import com.anastasiia.manicureschedulerbot.handler.message.MessageHandler
import com.anastasiia.manicureschedulerbot.model.UserMessageRequest
import com.anastasiia.manicureschedulerbot.sender.TelegramSender
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

@Component
class StartCommandHandler(
    private val telegramSender: TelegramSender
) : MessageHandler {

    private val command = "/start"
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun isApplicable(request: UserMessageRequest): Boolean {
        val message = request.message
        return message.hasText() &&
                message.isCommand() &&
                message.text.startsWith(command)
    }

    override fun handle(req: UserMessageRequest) {
        logger.info("Received update from user:${req.userId}")
        val sendMessage = SendMessage.builder()
            .chatId(req.userId)
            .text(req.message.text)
            .build()
        telegramSender.execute(sendMessage)
    }
}