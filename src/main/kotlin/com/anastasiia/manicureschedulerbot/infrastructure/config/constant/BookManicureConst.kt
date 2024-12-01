package com.anastasiia.manicureschedulerbot.infrastructure.config.constant

object BookManicureConst {
    const val BOOK_MANICURE_PREFIX = "book"
    const val CHOOSE_MANICURE_PREFIX = "$BOOK_MANICURE_PREFIX-manicure"
    const val CHOOSE_MANICURIST_PREFIX = "$BOOK_MANICURE_PREFIX-manicurist"

    const val IGNORE_CALLBACK = "ignore"
    const val NEXT_MANICURE_PAGE_CALLBACK = "$CHOOSE_MANICURE_PREFIX=next"
    const val PREV_MANICURE_PAGE_CALLBACK = "$CHOOSE_MANICURE_PREFIX=prev"

    const val NEXT_MANICURIST_PAGE_CALLBACK = "$CHOOSE_MANICURIST_PREFIX=next"
    const val PREV_MANICURIST_PAGE_CALLBACK = "$CHOOSE_MANICURIST_PREFIX=prev"
}
