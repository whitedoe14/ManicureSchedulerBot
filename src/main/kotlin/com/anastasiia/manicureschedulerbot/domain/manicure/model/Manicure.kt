package com.anastasiia.manicureschedulerbot.domain.manicure.model

import com.anastasiia.manicureschedulerbot.domain.manicure.valueobject.ManicureId
import com.anastasiia.manicureschedulerbot.domain.manicure.valueobject.Currency

data class Manicure (
    val manicureId: ManicureId?,
    val manicureName: String,
    val price: Int,
    val currency: Currency,
    val durationInMin: Int,
)
