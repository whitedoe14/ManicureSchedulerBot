package com.anastasiia.manicureschedulerbot.session.model

import com.anastasiia.manicureschedulerbot.state.TelegramUserState

data class UserSession (
    var userId: Long,
    var state: TelegramUserState
)