package com.anastasiia.manicureschedulerbot.infrastructure.cache.model

import com.anastasiia.manicureschedulerbot.infrastructure.cache.state.UserState
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

data class UserCallbackQueryRequest(
    var userId: Long,
    var callbackQuery: CallbackQuery,
    var userState: UserState,
)
