package com.anastasiia.manicureschedulerbot.infrastructure.cache.model

data class BookManicureForm(
    var manicureId: Long? = 0,
    var manicuristId: Long? = 0,
    // todo: change time type to correct
    var time: String? = "",
)
