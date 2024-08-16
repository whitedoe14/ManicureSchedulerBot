package com.anastasiia.manicureschedulerbot.exception.custom.cache

import com.anastasiia.manicureschedulerbot.exception.custom.cache.state.FormState
import com.anastasiia.manicureschedulerbot.exception.custom.cache.state.FormState.BEGIN
import org.springframework.stereotype.Component

@Component
class FormStateCache {
    private val userCache = HashMap<Long, FormState>()

    fun setState(userId: Long, state: FormState) {
        userCache[userId] = state
    }

    fun getState(userId: Long): FormState {
        return userCache[userId] ?: BEGIN
    }
}
