package com.anastasiia.manicureschedulerbot.domain.manicure.model

import com.anastasiia.manicureschedulerbot.domain.manicure.valueobject.Currency
import com.anastasiia.manicureschedulerbot.domain.manicure.valueobject.ManicureId

data class Manicure(
    val manicureId: ManicureId?,
    val manicureName: String,
    val price: Int,
    val currency: Currency,
    val durationInMin: Int,
)
