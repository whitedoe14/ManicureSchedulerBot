package com.anastasiia.manicureschedulerbot.handler.callbackquery.impl

import com.anastasiia.manicureschedulerbot.cache.form.BookManicureFormCache
import com.anastasiia.manicureschedulerbot.cache.page.ManicurePageCache
import com.anastasiia.manicureschedulerbot.cache.page.ManicuristPageCache
import com.anastasiia.manicureschedulerbot.config.const.BookManicureConst.BOOK_MANICURE_PREFIX
import com.anastasiia.manicureschedulerbot.config.const.BookManicureConst.NEXT_MANICURE_PAGE_CALLBACK
import com.anastasiia.manicureschedulerbot.config.const.BookManicureConst.NEXT_MANICURIST_PAGE_CALLBACK
import com.anastasiia.manicureschedulerbot.config.const.BookManicureConst.PREV_MANICURE_PAGE_CALLBACK
import com.anastasiia.manicureschedulerbot.config.const.BookManicureConst.PREV_MANICURIST_PAGE_CALLBACK
import com.anastasiia.manicureschedulerbot.handler.callbackquery.CallbackQueryHandler
import com.anastasiia.manicureschedulerbot.model.UserCallbackQueryRequest
import com.anastasiia.manicureschedulerbot.sender.TelegramSender
import com.anastasiia.manicureschedulerbot.service.ManicureService
import com.anastasiia.manicureschedulerbot.service.ManicuristService
import com.anastasiia.manicureschedulerbot.service.UserStateService
import com.anastasiia.manicureschedulerbot.state.BookManicureState.CHOOSE_MANICURE
import com.anastasiia.manicureschedulerbot.state.BookManicureState.CHOOSE_MANICURIST
import com.anastasiia.manicureschedulerbot.state.BookManicureState.CHOOSE_TIME
import com.anastasiia.manicureschedulerbot.util.KeyboardUtil
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText

@Component
class BookCallbackQueryHandler(
    private val telegramSender: TelegramSender,
    private val manicureService: ManicureService,
    private val manicuristService: ManicuristService,
    private val userStateService: UserStateService,
    private val keyboardUtil: KeyboardUtil,
    private val manicurePageCache: ManicurePageCache,
    private val manicuristPageCache: ManicuristPageCache,
    private val bookManicureFormCache: BookManicureFormCache,
) : CallbackQueryHandler {
    private val applicableStates = hashSetOf(CHOOSE_MANICURE, CHOOSE_MANICURIST, CHOOSE_TIME)

    override fun isApplicable(userRequest: UserCallbackQueryRequest): Boolean {
        return userRequest.callbackQuery.data.startsWith(BOOK_MANICURE_PREFIX) &&
            applicableStates.contains(userRequest.userState)
    }

    override fun handle(userRequest: UserCallbackQueryRequest) {
        when (userRequest.userState) {
            CHOOSE_MANICURE -> handleManicurePage(userRequest)
            CHOOSE_MANICURIST -> handleManicuristPage(userRequest)
        }
    }

    private fun handleManicurePage(userRequest: UserCallbackQueryRequest) {
        val data = userRequest.callbackQuery.data
        when (data) {
            NEXT_MANICURE_PAGE_CALLBACK -> handleNextManicurePage(userRequest)
            PREV_MANICURE_PAGE_CALLBACK -> handlePrevManicurePage(userRequest)
            else -> handleManicureChoice(userRequest)
        }
    }

    private fun handleNextManicurePage(req: UserCallbackQueryRequest) {
        val userId = req.userId
        val currentPage = manicurePageCache.getCurrentPage(userId)
        val pageNumber = manicureService.getTotalNumberOfManicurePages()
        if (currentPage + 1 == pageNumber) {
            return
        }
        val manicures = manicureService.getNextPageOfManicures(userId)
        val nextKeyboard = keyboardUtil.createManicureKeyboard(userId, manicures)
        val editMessageReq =
            EditMessageReplyMarkup.builder()
                .chatId(userId)
                .messageId(req.callbackQuery.message.messageId)
                .replyMarkup(nextKeyboard)
                .build()
        telegramSender.execute(editMessageReq)
    }

    private fun handlePrevManicurePage(req: UserCallbackQueryRequest) {
        val userId = req.userId
        val currentPage = manicurePageCache.getCurrentPage(userId)
        if (currentPage == 0) {
            return
        }
        val manicures = manicureService.getPrevPageOfManicures(userId)
        val prevKeyboard = keyboardUtil.createManicureKeyboard(userId, manicures)
        val editMessageReq =
            EditMessageReplyMarkup.builder()
                .chatId(userId)
                .messageId(req.callbackQuery.message.messageId)
                .replyMarkup(prevKeyboard)
                .build()
        telegramSender.execute(editMessageReq)
    }

    private fun handleManicureChoice(req: UserCallbackQueryRequest) {
        val manicureId = getCallbackDataValue(req)
        val userId = req.userId
        bookManicureFormCache.updateManicureId(userId, manicureId)
        userStateService.setUserState(userId, CHOOSE_MANICURIST)
        sendManicuristPage(req)
    }

    private fun sendManicuristPage(req: UserCallbackQueryRequest) {
        val userId = req.userId
        val manicurists = manicuristService.getCurrentPageOfManicurists(userId)
        val manicuristsKeyboard = keyboardUtil.createManicuristKeyboard(userId, manicurists)
        val editMessageReq =
            EditMessageText.builder()
                .chatId(userId)
                .text("Choose a manicure master:")
                .messageId(req.callbackQuery.message.messageId)
                .replyMarkup(manicuristsKeyboard)
                .build()
        telegramSender.execute(editMessageReq)
    }

    private fun getCallbackDataValue(req: UserCallbackQueryRequest): Long {
        val data = req.callbackQuery.data
        return data.substringAfter('=').toLong()
    }

    private fun handleManicuristPage(userRequest: UserCallbackQueryRequest) {
        val data = userRequest.callbackQuery.data
        when (data) {
            NEXT_MANICURIST_PAGE_CALLBACK -> handleNextManicuristPage(userRequest)
            PREV_MANICURIST_PAGE_CALLBACK -> handlePrevManicuristPage(userRequest)
            else -> handleManicuristChoice(userRequest)
        }
    }

    private fun handleNextManicuristPage(req: UserCallbackQueryRequest) {
        val userId = req.userId
        val currentPage = manicuristPageCache.getCurrentPage(userId)
        val totalNumOfPages = manicuristService.getTotalNumberOfManicuristPages()
        if (currentPage + 1 == totalNumOfPages) {
            return
        }
        sendNextManicuristPage(userId, req)
    }

    private fun sendNextManicuristPage(
        userId: Long,
        req: UserCallbackQueryRequest,
    ) {
        val nextManicuristsPage = manicuristService.getNextPageOfManicurists(userId)
        val nextKeyboard = keyboardUtil.createManicuristKeyboard(userId, nextManicuristsPage)
        val editMessageReq =
            EditMessageReplyMarkup.builder()
                .chatId(userId)
                .messageId(req.callbackQuery.message.messageId)
                .replyMarkup(nextKeyboard)
                .build()
        telegramSender.execute(editMessageReq)
    }

    private fun handlePrevManicuristPage(req: UserCallbackQueryRequest) {
        val userId = req.userId
        val currentPage = manicuristPageCache.getCurrentPage(userId)
        if (currentPage == 0) {
            return
        }
        sendPrevManicuristPage(userId, req)
    }

    private fun sendPrevManicuristPage(
        userId: Long,
        req: UserCallbackQueryRequest,
    ) {
        val prevManicuristsPage = manicuristService.getPrevPageOfManicurists(userId)
        val prevKeyboard = keyboardUtil.createManicuristKeyboard(userId, prevManicuristsPage)
        val editMessageReq =
            EditMessageReplyMarkup.builder()
                .chatId(userId)
                .messageId(req.callbackQuery.message.messageId)
                .replyMarkup(prevKeyboard)
                .build()
        telegramSender.execute(editMessageReq)
    }

    private fun handleManicuristChoice(req: UserCallbackQueryRequest) {
        val manicuristId = getCallbackDataValue(req)
        val userId = req.userId
        bookManicureFormCache.updateManicuristId(userId, manicuristId)
        userStateService.setUserState(userId, CHOOSE_TIME)
        // todo: send mini app with frontend and handle choose_time in another handler
    }
}
