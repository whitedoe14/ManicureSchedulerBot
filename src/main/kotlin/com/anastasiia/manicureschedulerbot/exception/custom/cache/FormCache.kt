package com.anastasiia.manicureschedulerbot.exception.custom.cache

import com.anastasiia.manicureschedulerbot.exception.custom.cache.entity.FormCacheEntity
import org.springframework.stereotype.Component

@Component
class FormCache {

    private val formCache = HashMap<Long, FormCacheEntity>()

    fun setUsername(userId: Long, username: String) {
        if (!formCache.containsKey(userId)) {
            createNewForm(userId)
        }
        val cachedForm = formCache[userId]!!
        cachedForm.username = username
    }

    fun setAge(userId: Long, age: Int) {
        if (!formCache.containsKey(userId)) {
            createNewForm(userId)
        }
        val cachedForm = formCache[userId]!!
        cachedForm.age = age
    }

    fun setCity(userId: Long, city: String) {
        if (!formCache.containsKey(userId)) {
            createNewForm(userId)
        }
        val cachedForm = formCache[userId]!!
        cachedForm.city = city
    }

    fun getForm(userId: Long) : FormCacheEntity? {
        return formCache[userId]

    }

    private fun createNewForm(userId: Long) {
        formCache[userId] = FormCacheEntity()
    }

}