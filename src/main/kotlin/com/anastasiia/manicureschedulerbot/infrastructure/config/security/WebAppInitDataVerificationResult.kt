package com.anastasiia.manicureschedulerbot.infrastructure.config.security

sealed class WebAppInitDataVerificationResult {
    data class SuccessWebAppInitDataVerificationResult(val webAppInitData: Map<String, String>) : WebAppInitDataVerificationResult()

    data class ExceptionWebAppInitDataVerificationResult(val exception: Exception) : WebAppInitDataVerificationResult()

    data class FailWebAppInitDataVerificationResult(val message: String?) : WebAppInitDataVerificationResult()
}
