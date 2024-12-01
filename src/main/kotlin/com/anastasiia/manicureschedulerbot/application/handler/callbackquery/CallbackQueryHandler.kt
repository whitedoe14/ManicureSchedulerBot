package com.anastasiia.manicureschedulerbot.application.handler.callbackquery

import com.anastasiia.manicureschedulerbot.infrastructure.cache.model.UserCallbackQueryRequest

interface CallbackQueryHandler {
    fun isApplicable(userRequest: UserCallbackQueryRequest): Boolean

    fun handle(userRequest: UserCallbackQueryRequest)
}
