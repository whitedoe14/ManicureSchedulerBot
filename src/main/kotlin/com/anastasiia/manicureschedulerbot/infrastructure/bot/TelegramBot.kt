package com.anastasiia.manicureschedulerbot.infrastructure.bot

import com.anastasiia.manicureschedulerbot.application.dispatcher.UpdateDispatcher
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class TelegramBot(
    @Value("\${telegram.bot-token}") botToken: String,
    @Value("\${telegram.username}") private val username: String,
    private val updateDispatcher: UpdateDispatcher,
) : TelegramLongPollingBot(botToken) {
    override fun getBotUsername(): String {
        return username
    }

    override fun onUpdateReceived(update: Update) {
        updateDispatcher.dispatch(update)
    }
}
