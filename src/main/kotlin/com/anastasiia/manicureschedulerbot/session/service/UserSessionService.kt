package com.anastasiia.manicureschedulerbot.session.service

import com.anastasiia.manicureschedulerbot.state.TelegramMenuState
import com.anastasiia.manicureschedulerbot.session.model.UserSession
import org.springframework.stereotype.Service

@Service
class UserSessionService {
    private val cache = HashMap<Long, UserSession>()

    fun getUserSession(userId: Long): UserSession {
        return cache.getOrDefault(userId, UserSession(userId, TelegramMenuState.ON_MAIN_MENU))
    }

    fun setTelegramUserSession(userId: Long, session: UserSession) {
        cache[userId] = session
    }
}