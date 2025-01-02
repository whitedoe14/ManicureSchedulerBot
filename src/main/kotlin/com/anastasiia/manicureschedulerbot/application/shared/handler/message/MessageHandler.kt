package com.anastasiia.manicureschedulerbot.application.shared.handler.message

import com.anastasiia.manicureschedulerbot.infrastructure.cache.model.UserMessageRequest

interface MessageHandler {
    fun isApplicable(request: UserMessageRequest): Boolean

    fun handle(req: UserMessageRequest)
}
