package com.anastasiia.manicureschedulerbot.model

import com.anastasiia.manicureschedulerbot.session.model.UserSession
import org.telegram.telegrambots.meta.api.objects.Message

data class UserMessageRequest(
    var userId: Long,
    var message: Message,
    var userSession: UserSession,
)
