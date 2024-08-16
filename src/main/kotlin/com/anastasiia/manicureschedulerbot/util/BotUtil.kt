package com.anastasiia.manicureschedulerbot.util

import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.exceptions.TelegramApiException

object BotUtil {

    fun execute(sendMessage: SendMessage, bot: AbsSender) {
        try {
            bot.execute(sendMessage)
        } catch (e: TelegramApiException) {
            throw e
        }
    }
}