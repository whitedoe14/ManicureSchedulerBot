package com.anastasiia.manicureschedulerbot.handler.update

import org.telegram.telegrambots.meta.api.objects.Update

interface UpdateHandler {
    fun isApplicable(update: Update): Boolean

    fun handle(update: Update)
}
