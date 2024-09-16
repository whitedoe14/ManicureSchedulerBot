package com.anastasiia.manicureschedulerbot.util

import com.anastasiia.manicureschedulerbot.cache.page.ManicurePageCache
import com.anastasiia.manicureschedulerbot.cache.page.ManicuristPageCache
import com.anastasiia.manicureschedulerbot.config.const.BookManicureConst.CHOOSE_MANICURE_PREFIX
import com.anastasiia.manicureschedulerbot.config.const.BookManicureConst.CHOOSE_MANICURIST_PREFIX
import com.anastasiia.manicureschedulerbot.config.const.BookManicureConst.IGNORE_CALLBACK
import com.anastasiia.manicureschedulerbot.config.const.BookManicureConst.NEXT_MANICURE_PAGE_CALLBACK
import com.anastasiia.manicureschedulerbot.config.const.BookManicureConst.NEXT_MANICURIST_PAGE_CALLBACK
import com.anastasiia.manicureschedulerbot.config.const.BookManicureConst.PREV_MANICURE_PAGE_CALLBACK
import com.anastasiia.manicureschedulerbot.config.const.BookManicureConst.PREV_MANICURIST_PAGE_CALLBACK
import com.anastasiia.manicureschedulerbot.database.entity.ManicureEntity
import com.anastasiia.manicureschedulerbot.database.entity.ManicuristEntity
import com.anastasiia.manicureschedulerbot.service.ManicureService
import com.anastasiia.manicureschedulerbot.service.ManicuristService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo

@Component
class KeyboardUtil(
    private val manicurePageCache: ManicurePageCache,
    private val manicuristPageCache: ManicuristPageCache,
    private val manicureService: ManicureService,
    private val manicuristService: ManicuristService,
    @Value("\${telegram.time-picker-url}") private val timePickerUrl: String,
) {
    fun createManicureKeyboard(
        userId: Long,
        manicures: List<ManicureEntity>,
    ): InlineKeyboardMarkup {
        val keyboardBuilder = InlineKeyboardMarkup.builder()
        buildManicurePage(manicures, keyboardBuilder)
        buildManicureFooter(userId, keyboardBuilder)
        return keyboardBuilder.build()
    }

    private fun buildManicureFooter(
        userId: Long,
        keyboardBuilder: InlineKeyboardMarkup.InlineKeyboardMarkupBuilder,
    ) {
        val prev = InlineKeyboardButton("⬅ previous")
        prev.callbackData = PREV_MANICURE_PAGE_CALLBACK

        val manicuresPageNumber = manicureService.getTotalNumberOfManicurePages()
        val currentPage = manicurePageCache.getCurrentPage(userId) + 1 // count pages from 0
        val pages = InlineKeyboardButton("$currentPage / $manicuresPageNumber")
        pages.callbackData = IGNORE_CALLBACK

        val next = InlineKeyboardButton("next ➡")
        next.callbackData = NEXT_MANICURE_PAGE_CALLBACK
        val footerRow = listOf(prev, pages, next)
        keyboardBuilder.keyboardRow(footerRow)
    }

    private fun buildManicurePage(
        manicures: List<ManicureEntity>,
        keyboardBuilder: InlineKeyboardMarkup.InlineKeyboardMarkupBuilder,
    ) {
        var i = 0
        while (i < manicures.size) {
            val manicure = manicures[i]

            val btn1 = InlineKeyboardButton(manicure.name)
            btn1.callbackData = "$CHOOSE_MANICURE_PREFIX=${manicure.id}"

            if (i + 1 < manicures.size) {
                val nextManicure = manicures[i + 1]
                val btn2 = InlineKeyboardButton(nextManicure.name)
                btn2.callbackData = "$CHOOSE_MANICURE_PREFIX=${nextManicure.id}"
                keyboardBuilder.keyboardRow(listOf(btn1, btn2))
                i++
            } else {
                keyboardBuilder.keyboardRow(listOf(btn1))
            }
            i++
        }
    }

    fun createManicuristKeyboard(
        userId: Long,
        manicurists: List<ManicuristEntity>,
    ): InlineKeyboardMarkup {
        val keyboardBuilder = InlineKeyboardMarkup.builder()
        buildManicuristPage(manicurists, keyboardBuilder)
        buildManicuristFooter(userId, keyboardBuilder)

        return keyboardBuilder.build()
    }

    private fun buildManicuristFooter(
        userId: Long,
        keyboardBuilder: InlineKeyboardMarkup.InlineKeyboardMarkupBuilder,
    ) {
        val prev = InlineKeyboardButton("⬅ previous")
        prev.callbackData = PREV_MANICURIST_PAGE_CALLBACK

        val manicuristsPageNumber = manicuristService.getTotalNumberOfManicuristPages()
        val currentPage = manicuristPageCache.getCurrentPage(userId) + 1
        val pages = InlineKeyboardButton("$currentPage / $manicuristsPageNumber")
        pages.callbackData = IGNORE_CALLBACK

        val next = InlineKeyboardButton("next ➡")
        next.callbackData = NEXT_MANICURIST_PAGE_CALLBACK

        val footerRow = listOf(prev, pages, next)
        keyboardBuilder.keyboardRow(footerRow)
    }

    private fun buildManicuristPage(
        manicurists: List<ManicuristEntity>,
        keyboardBuilder: InlineKeyboardMarkup.InlineKeyboardMarkupBuilder,
    ) {
        var i = 0
        while (i < manicurists.size) {
            val manicurist = manicurists[i]

            val btn1 = InlineKeyboardButton(manicurist.fullName)
            btn1.callbackData = "$CHOOSE_MANICURIST_PREFIX=${manicurist.id}"

            if (i + 1 < manicurists.size) {
                val nextManicurist = manicurists[i + 1]

                val btn2 = InlineKeyboardButton(nextManicurist.fullName)
                btn2.callbackData = "$CHOOSE_MANICURIST_PREFIX=${nextManicurist.id}"

                keyboardBuilder.keyboardRow(listOf(btn1, btn2))
                i++
            } else {
                keyboardBuilder.keyboardRow(listOf(btn1))
            }
            i++
        }
    }

    fun createTimePickerKeyboard(): InlineKeyboardMarkup {
        val keyboardBuilder = InlineKeyboardMarkup.builder()
        val webAppInfo = WebAppInfo("$timePickerUrl/book")
        val miniAppButton = InlineKeyboardButton.builder()
            .text("Choose time")
            .webApp(webAppInfo)
            .build()
        keyboardBuilder.keyboardRow(arrayListOf(miniAppButton))
        return keyboardBuilder.build()
    }

    // todo: remove
    fun createKebabKeyboard(): ReplyKeyboardMarkup {
        val keyboardBuilder = ReplyKeyboardMarkup.builder()
        val row = KeyboardRow()
        val btn = KeyboardButton("Hi! 🥟")
        row.add(btn)
        keyboardBuilder.keyboardRow(row)
        keyboardBuilder.resizeKeyboard(false)
        return keyboardBuilder.build()
    }
}
