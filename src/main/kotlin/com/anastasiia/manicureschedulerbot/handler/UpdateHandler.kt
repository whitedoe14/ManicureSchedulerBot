package com.anastasiia.manicureschedulerbot.handler

import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.bots.AbsSender

interface UpdateHandler {
    fun handle(update: Update, bot: AbsSender)
    fun isApplicable(update: Update): Boolean
}