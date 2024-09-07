package com.anastasiia.manicureschedulerbot.model

import com.anastasiia.manicureschedulerbot.state.UserState
import org.telegram.telegrambots.meta.api.objects.Message

data class UserMessageRequest(
    var userId: Long,
    var message: Message,
    var userState: UserState,
)
