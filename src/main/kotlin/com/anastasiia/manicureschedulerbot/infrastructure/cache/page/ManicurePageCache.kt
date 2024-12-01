package com.anastasiia.manicureschedulerbot.infrastructure.cache.page

import org.springframework.stereotype.Component

@Component
class ManicurePageCache {
    private val manicurePageCache = HashMap<Long, Int>() // UserId -> page

    fun getCurrentPage(userId: Long): Int {
        return manicurePageCache[userId] ?: 0
    }

    fun setNextPage(userId: Long) {
        val currentPage = getCurrentPage(userId)
        manicurePageCache[userId] = currentPage + 1
    }

    fun setPrevPage(userId: Long) {
        val currentPage = getCurrentPage(userId)
        if (currentPage == 0) {
            return
        }
        manicurePageCache[userId] = currentPage - 1
    }
}
