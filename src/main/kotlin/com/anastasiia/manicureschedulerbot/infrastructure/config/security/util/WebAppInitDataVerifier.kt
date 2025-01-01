package com.anastasiia.manicureschedulerbot.infrastructure.config.security.util

import com.anastasiia.manicureschedulerbot.infrastructure.config.security.util.WebAppInitDataVerificationResult.ExceptionWebAppInitDataVerificationResult
import com.anastasiia.manicureschedulerbot.infrastructure.config.security.util.WebAppInitDataVerificationResult.FailWebAppInitDataVerificationResult
import com.anastasiia.manicureschedulerbot.infrastructure.config.security.util.WebAppInitDataVerificationResult.SuccessWebAppInitDataVerificationResult
import jakarta.servlet.http.HttpServletRequest
import org.apache.commons.codec.digest.HmacUtils
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders.AUTHORIZATION
import java.net.URLDecoder
import java.nio.charset.StandardCharsets.UTF_8
import java.util.TreeMap

object WebAppInitDataVerifier {
    private const val BEARER = "Bearer"
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun verify(request: HttpServletRequest, botToken: String): WebAppInitDataVerificationResult {
        if (!hasWebAppInitData(request)) {
            logger.warn("$AUTHORIZATION header is null or empty")
            return FailWebAppInitDataVerificationResult("$AUTHORIZATION header is null or empty")
        }
        val rawWebAppInitData = getRawWebAppInitData(request)
        val webAppInitDataParseResult = parseWebAppInitDataSafely(rawWebAppInitData)
        if (webAppInitDataParseResult !is SuccessWebAppInitDataVerificationResult) {
            return webAppInitDataParseResult
        }
        val webAppInitData = webAppInitDataParseResult.webAppInitData
        if (!verifyHash(webAppInitData, botToken)) {
            logger.warn("Hash verification failed for webAppInitData: $webAppInitData")
            return FailWebAppInitDataVerificationResult("Hash doesn't match")
        }
        return webAppInitDataParseResult
    }

    private fun parseWebAppInitData(rawWebAppInitData: String): Map<String, String> {
        val webAppInitData = TreeMap<String, String>()
        val webAppInitDataPairs = rawWebAppInitData.split("&")
        for (pair in webAppInitDataPairs) {
            val separatorIndex = pair.indexOf("=")
            val key = getKeyFromWebAppInitDataPair(separatorIndex, pair)
            val value = getValueFromWebAppInitDataPair(separatorIndex, pair)
            webAppInitData[key] = value
        }
        checkWebAppInitData(webAppInitData)
        return webAppInitData
    }

    private fun verifyHash(
        webAppInitData: Map<String, String>,
        botToken: String,
    ): Boolean {
        val dataCheckString = createDataCheckString(webAppInitData)
        val generatedHash = generateHash(botToken, dataCheckString)
        val webAppInitDataHash = webAppInitData["hash"]
        return generatedHash == webAppInitDataHash
    }

    private fun generateHash(botToken: String, dataCheckString: String): String? {
        val hmacSha256Algorithm = "HmacSHA256"
        val secretKey = HmacUtils(hmacSha256Algorithm, "WebAppData").hmac(botToken)
        val generatedHash = HmacUtils(hmacSha256Algorithm, secretKey).hmacHex(dataCheckString)
        return generatedHash
    }

    private fun parseWebAppInitDataSafely(rawWebAppInitData: String): WebAppInitDataVerificationResult {
        return try {
            SuccessWebAppInitDataVerificationResult(parseWebAppInitData(rawWebAppInitData))
        } catch (e: Exception) {
            logger.warn("Exception during parsing webAppInitData", e)
            ExceptionWebAppInitDataVerificationResult(e)
        }
    }

    private fun createDataCheckString(webAppInitData: Map<String, String>): String {
        return webAppInitData.entries
            .filterNot { it.key.startsWith("hash") }
            .joinToString("\n") { pair ->
                "${pair.key}=${pair.value}"
            }
    }

    private fun checkWebAppInitData(webAppInitData: Map<String, String?>) {
        listOf("query_id", "hash").forEach { key ->
            if (webAppInitData[key].isNullOrBlank()) {
                logger.warn("Missing or empty value for key: $key")
                throw WebAppInitDataException("Missing or empty value for key: $key")
            }
        }
    }

    private fun getKeyFromWebAppInitDataPair(separatorIndex: Int, pair: String): String {
        if (separatorIndex == 0) {
            logger.warn("WebAppInitData is malformed: $pair")
            throw WebAppInitDataException("Init data is malformed")
        }
        val key = pair.substring(0, separatorIndex)
        return URLDecoder.decode(key, UTF_8)
    }

    private fun getValueFromWebAppInitDataPair(separatorIndex: Int, pair: String): String {
        val isValueExist = pair.length == separatorIndex + 1
        if (separatorIndex == 0 || isValueExist) {
            logger.warn("WebAppInitData is malformed: $pair")
            throw WebAppInitDataException("WebAppInitData is malformed")
        }
        val value = pair.substring(separatorIndex + 1)
        return URLDecoder.decode(value, UTF_8)
    }

    private fun hasWebAppInitData(request: HttpServletRequest): Boolean {
        val webAppInitData = request.getHeader(AUTHORIZATION)?.removePrefix("$BEARER ")
        return !webAppInitData.isNullOrBlank()
    }

    private fun getRawWebAppInitData(request: HttpServletRequest): String {
        val authHeader = request.getHeader(AUTHORIZATION)
        return authHeader.removePrefix("$BEARER ")
    }
}
