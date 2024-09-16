package com.anastasiia.manicureschedulerbot.util

import com.anastasiia.manicureschedulerbot.exception.custom.InitDataException
import org.apache.commons.codec.digest.HmacUtils
import org.slf4j.LoggerFactory
import java.net.URLDecoder
import java.nio.charset.StandardCharsets.UTF_8
import java.util.TreeMap

object TelegramAuthUtil {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun isValid(rawInitData: String, botToken: String): Boolean {
        val initData = parseInitDataSafely(rawInitData) ?: return false
        return verifyHash(initData, botToken)
    }

    fun parseInitData(rawInitData: String): Map<String, String> {
        val initData = TreeMap<String, String>()
        val initDataPairs = rawInitData.split("&")
        for (initDataPair in initDataPairs) {
            val separatorIndex = initDataPair.indexOf("=")
            val key = getKeyFromInitDataPair(separatorIndex, initDataPair)
            val value = getValueFromInitDataPair(separatorIndex, initDataPair)
            initData[key] = value
        }
        checkInitData(initData)
        return initData
    }

    private fun verifyHash(
        initData: Map<String, String>,
        botToken: String,
    ): Boolean {
        val dataCheckString = createDataCheckString(initData)
        val generatedHash = generateHash(botToken, dataCheckString)
        val initDataHash = initData["hash"]
        return generatedHash == initDataHash
    }

    private fun generateHash(botToken: String, dataCheckString: String): String? {
        val hmacSha256Algorithm = "HmacSHA256"
        val secretKey = HmacUtils(hmacSha256Algorithm, "WebAppData").hmac(botToken)
        val generatedHash = HmacUtils(hmacSha256Algorithm, secretKey).hmacHex(dataCheckString)
        return generatedHash
    }

    private fun parseInitDataSafely(rawInitData: String): Map<String, String>? {
        if (rawInitData.isBlank()) {
            return null
        }
        return try {
            parseInitData(rawInitData)
        } catch (e: InitDataException) {
            logger.debug(e.message)
            null
        }
    }

    private fun createDataCheckString(initData: Map<String, String>): String {
        return initData.entries
            .filterNot { it.key.startsWith("hash") }
            .joinToString("\n") { pair ->
                "${pair.key}=${pair.value}"
            }
    }

    private fun checkInitData(initData: Map<String, String?>) {
        listOf("query_id", "hash").forEach { key ->
            if (initData[key].isNullOrBlank()) {
                throw InitDataException("Missing or empty value for key: $key")
            }
        }
    }

    private fun getKeyFromInitDataPair(separatorIndex: Int, pair: String): String {
        if (separatorIndex == 0) {
            throw InitDataException("Init data is malformed")
        }
        return URLDecoder.decode(pair.substring(0, separatorIndex), UTF_8)
    }

    private fun getValueFromInitDataPair(separatorIndex: Int, pair: String): String {
        if (separatorIndex == 0 || pair.length == separatorIndex + 1) {
            throw InitDataException("Init data is malformed")
        }
        return URLDecoder.decode(pair.substring(separatorIndex + 1), UTF_8)
    }
}
