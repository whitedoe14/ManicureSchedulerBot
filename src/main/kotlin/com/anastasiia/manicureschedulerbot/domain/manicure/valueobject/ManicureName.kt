package com.anastasiia.manicureschedulerbot.domain.manicure.valueobject

import com.anastasiia.manicureschedulerbot.domain.common.ValueObject

enum class ManicureName (private val manicureName: String) : ValueObject {
    FRENCH("french"),
    PRESS_ON("press-on"),
    CLASSIC("classic"),
    GEL("gel"),
    PLEXIGEL("plexigel"),
    SHELLAC("shellac"),
    DIP_POWDER1("dip-powder1"),
    DIP_POWDER2("dip-powder2"),
    DIP_POWDER3("dip-powder3"),
}
