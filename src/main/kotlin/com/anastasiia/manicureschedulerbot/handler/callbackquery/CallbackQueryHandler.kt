package com.anastasiia.manicureschedulerbot.handler.callbackquery

import com.anastasiia.manicureschedulerbot.model.UserCallbackQueryRequest

interface CallbackQueryHandler {
    fun isApplicable(userRequest: UserCallbackQueryRequest): Boolean

    fun handle(userRequest: UserCallbackQueryRequest)
}
