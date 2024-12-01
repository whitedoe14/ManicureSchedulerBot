package com.anastasiia.manicureschedulerbot.application.dispatcher

import com.anastasiia.manicureschedulerbot.application.handler.update.UpdateHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.telegram.telegrambots.meta.api.objects.Update

@Service
class UpdateDispatcher(
    private val updateHandlers: List<UpdateHandler>,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun dispatch(update: Update) {
        for (updateHandler in updateHandlers) {
            if (updateHandler.isApplicable(update)) {
                updateHandler.handle(update)
                return
            }
        }
        logger.warn("No such handler for the update.")
    }
}
