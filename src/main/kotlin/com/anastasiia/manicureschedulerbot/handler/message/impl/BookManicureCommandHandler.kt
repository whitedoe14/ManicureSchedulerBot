package com.anastasiia.manicureschedulerbot.handler.message.impl

import com.anastasiia.manicureschedulerbot.handler.message.MessageHandler
import com.anastasiia.manicureschedulerbot.model.UserMessageRequest
import com.anastasiia.manicureschedulerbot.sender.TelegramSender
import com.anastasiia.manicureschedulerbot.service.ManicureService
import com.anastasiia.manicureschedulerbot.service.UserStateService
import com.anastasiia.manicureschedulerbot.state.BookManicureState
import com.anastasiia.manicureschedulerbot.util.KeyboardUtil
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage

@Component
class BookManicureCommandHandler(
    private val telegramSender: TelegramSender,
    private val keyboardUtil: KeyboardUtil,
    private val manicureService: ManicureService,
    private val userStateService: UserStateService,
) : MessageHandler {
    private val command = "/book_manicure"
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun isApplicable(request: UserMessageRequest): Boolean {
        val message = request.message
        return message.hasText() &&
            message.isCommand &&
            message.text.startsWith(command) // todo: add states
    }

    override fun handle(req: UserMessageRequest) {
        val manicures = manicureService.getCurrentPageOfManicures(req.userId)
        val sendMessage =
            SendMessage.builder()
                .chatId(req.userId)
                .text("Choose a manicure type")
                .replyMarkup(keyboardUtil.createManicureKeyboard(req.userId, manicures))
                .build()
        telegramSender.execute(sendMessage)
        userStateService.setUserState(req.userId, BookManicureState.CHOOSE_MANICURE)
    }
}
