package com.anastasiia.manicureschedulerbot.cache.page

import org.springframework.stereotype.Component

@Component
class ManicuristPageCache {
    private val manicuristPageCache = HashMap<Long, Int>() // userId -> pageNumber

    fun getCurrentPage(userId: Long): Int {
        return manicuristPageCache[userId] ?: 0
    }

    fun setNextPage(userId: Long) {
        val currentPage = getCurrentPage(userId)
        manicuristPageCache[userId] = currentPage + 1
    }

    fun setPrevPage(userId: Long) {
        val currentPage = getCurrentPage(userId)
        if (currentPage == 0) {
            return
        }
        manicuristPageCache[userId] = currentPage - 1
    }
}
