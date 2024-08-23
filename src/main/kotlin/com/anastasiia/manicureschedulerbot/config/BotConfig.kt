package com.anastasiia.manicureschedulerbot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiException
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession
import kotlin.jvm.Throws

@Configuration
class BotConfig {
    @Bean
    @Throws(TelegramApiException::class)
    fun registerBot(bot: TelegramLongPollingBot): TelegramBotsApi {
        val telegramBotsApi = TelegramBotsApi(DefaultBotSession::class.java)
        telegramBotsApi.registerBot(bot)
        return telegramBotsApi
    }
}
