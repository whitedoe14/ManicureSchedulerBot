package com.anastasiia.manicureschedulerbot.infrastructure.cache.form

import com.anastasiia.manicureschedulerbot.infrastructure.cache.model.BookManicureForm
import org.springframework.stereotype.Component

@Component
class BookManicureFormCache {
    private val manicureFormCache = HashMap<Long, BookManicureForm>()

    fun getForm(userId: Long): BookManicureForm {
        return manicureFormCache[userId] ?: BookManicureForm()
    }

    fun saveForm(
        userId: Long,
        form: BookManicureForm,
    ) {
        manicureFormCache[userId] = form
    }

    fun updateManicureId(
        userId: Long,
        manicureId: Long,
    ) {
        val form = getForm(userId)
        form.manicureId = manicureId
        saveForm(userId, form)
    }

    fun updateManicuristId(
        userId: Long,
        manicuristId: Long,
    ) {
        val form = getForm(userId)
        form.manicuristId = manicuristId
        saveForm(userId, form)
    }
}
