package com.anastasiia.manicureschedulerbot.infrastructure.cache.model

import com.anastasiia.manicureschedulerbot.infrastructure.cache.state.UserState
import org.telegram.telegrambots.meta.api.objects.Message

data class UserMessageRequest(
    var userId: Long,
    var message: Message,
    var userState: UserState,
)
