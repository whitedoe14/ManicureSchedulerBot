package com.anastasiia.manicureschedulerbot.dispatcher

import com.anastasiia.manicureschedulerbot.exception.custom.NoSuchHandlerException
import com.anastasiia.manicureschedulerbot.handler.UpdateHandler
import org.springframework.stereotype.Service

import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender


@Service
class UpdateDispatcher(
    private val updateHandlers: List<UpdateHandler>
) {

    fun dispatch(update: Update, bot: AbsSender) {
        update.hasCallbackQuery()
        val handler = getHandler(update)
        handler.handle(update, bot)
    }

    private fun getHandler(update: Update): UpdateHandler {
        for (updateHandler in updateHandlers) {
            if (updateHandler.isApplicable(update)) {
                return updateHandler
            }
        }
        throw NoSuchHandlerException()
    }
}