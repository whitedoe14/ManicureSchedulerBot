package com.anastasiia.manicureschedulerbot.sender

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.DefaultAbsSender
import org.telegram.telegrambots.bots.DefaultBotOptions

@Component
class TelegramSender(
    @Value("\${telegram.bot-token}") botToken: String,
) : DefaultAbsSender(
    DefaultBotOptions(),
    botToken
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

//    fun <Method: BotApiMethod<*>> executeMethod(method: Method) {
//        try {
//            super.execute()
//        }catch (e: TelegramApiException) {
//            logger.warn(e.message)
//        }
//    }
}