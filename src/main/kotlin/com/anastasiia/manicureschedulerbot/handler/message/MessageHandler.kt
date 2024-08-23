package com.anastasiia.manicureschedulerbot.handler.message

import com.anastasiia.manicureschedulerbot.model.UserMessageRequest

interface MessageHandler {
    fun isApplicable(request: UserMessageRequest): Boolean

    fun handle(req: UserMessageRequest)
}
