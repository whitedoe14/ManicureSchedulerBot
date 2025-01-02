package com.anastasiia.manicureschedulerbot.application

import io.kotest.core.spec.style.FunSpec

class BookingAlgorithmTest() : FunSpec({
    val testable = BookEventService()

    test("test") {
        // Act
        val actual = testable.main()
    }
})
