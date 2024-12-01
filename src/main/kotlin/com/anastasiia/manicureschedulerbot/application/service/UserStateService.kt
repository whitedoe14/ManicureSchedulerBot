package com.anastasiia.manicureschedulerbot.application.service

import com.anastasiia.manicureschedulerbot.application.state.MenuState
import com.anastasiia.manicureschedulerbot.application.state.UserState
import org.springframework.stereotype.Service

@Service
class UserStateService {
    private val cache = HashMap<Long, UserState>()

    fun getUserState(userId: Long): UserState {
        return cache.getOrDefault(userId, MenuState.ON_MAIN_MENU)
    }

    fun setUserState(
        userId: Long,
        state: UserState,
    ) {
        cache[userId] = state
    }
}