package com.anastasiia.manicureschedulerbot.model

import com.anastasiia.manicureschedulerbot.state.UserState
import org.telegram.telegrambots.meta.api.objects.CallbackQuery

data class UserCallbackQueryRequest(
    var userId: Long,
    var callbackQuery: CallbackQuery,
    var userState: UserState,
)
