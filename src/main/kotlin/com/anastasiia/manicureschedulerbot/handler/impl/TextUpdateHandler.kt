package com.anastasiia.manicureschedulerbot.handler.impl

import com.anastasiia.manicureschedulerbot.exception.custom.cache.FormCache
import com.anastasiia.manicureschedulerbot.exception.custom.cache.FormStateCache
import com.anastasiia.manicureschedulerbot.exception.custom.cache.state.FormState.BEGIN
import com.anastasiia.manicureschedulerbot.exception.custom.cache.state.FormState.AGE
import com.anastasiia.manicureschedulerbot.exception.custom.cache.state.FormState.USERNAME
import com.anastasiia.manicureschedulerbot.exception.custom.cache.state.FormState.CITY
import com.anastasiia.manicureschedulerbot.handler.UpdateHandler
import com.anastasiia.manicureschedulerbot.util.BotUtil.execute
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.ParseMode
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender


@Component
class TextUpdateHandler(
    private val formStateCache: FormStateCache,
    private val formCache: FormCache
) : UpdateHandler {

    override fun handle(update: Update, bot: AbsSender) {
        val userId = update.message.from.id
        val state = formStateCache.getState(userId)
        when (state) {
            BEGIN -> handleBeginState(userId, bot)
            USERNAME -> handleUsernameState(userId, update, bot)
            AGE -> handleAgeState(userId, update, bot)
            CITY -> handleCityState(userId, update, bot)
        }
    }

    override fun isApplicable(update: Update): Boolean {
        return update.hasMessage() &&
                update.message.hasText()
    }

    private fun handleBeginState(userId: Long, bot: AbsSender) {
        sendUsernameMessage(userId, bot)
        formStateCache.setState(userId, USERNAME)
    }

    private fun sendUsernameMessage(userId: Long, bot: AbsSender) {
        val sendMessage = SendMessage.builder()
            .chatId(userId)
            .text("Send your username:")
            .build()
        execute(sendMessage, bot)
    }

    private fun sendAgeMessage(userId: Long, bot: AbsSender) {
        val sendMessage = SendMessage.builder()
            .chatId(userId)
            .text("Send your age:")
            .build()
        execute(sendMessage, bot)
    }

    private fun sendCityMessage(userId: Long, bot: AbsSender) {
        val sendMessage = SendMessage.builder()
            .chatId(userId)
            .text("Send your city:")
            .build()
        execute(sendMessage, bot)
    }

    private fun handleUsernameState(userId: Long, update: Update, bot: AbsSender) {
        setUsername(userId, update)
        formStateCache.setState(userId, AGE)
        sendAgeMessage(userId, bot)
    }

    private fun handleCityState(userId: Long, update: Update, bot: AbsSender) {
        setCity(userId, update)
        formStateCache.setState(userId, BEGIN)
        sendSuccessfulFilledFormMessage(userId, bot)
    }

    private fun setUsername(userId: Long, update: Update) {
        val username = update.message.text
        formCache.setUsername(userId, username)
    }

    private fun setAge(userId: Long, update: Update) {
        val age = update.message.text.toInt()  // Todo: check
        formCache.setAge(userId, age)
    }

    private fun setCity(userId: Long, update: Update) {
        val city = update.message.text
        formCache.setCity(userId, city)
    }

    private fun handleAgeState(userId: Long, update: Update, bot: AbsSender) {
        setAge(userId, update)
        formStateCache.setState(userId, CITY)
        sendCityMessage(userId, bot)
    }

    private fun sendSuccessfulFilledFormMessage(userId: Long, bot: AbsSender) {
        val form = formCache.getForm(userId)!!
        val sendMessage = SendMessage.builder()
            .chatId(userId)
            .text(
                """The form was successfully filled
                |<b>username:</b> ${form.username}
                |<b>age:</b> ${form.age}
                |<b>city:</b> ${form.city}
            """.trimMargin()
            )
            .parseMode(ParseMode.HTML)
            .build()
        execute(sendMessage, bot)
    }
}