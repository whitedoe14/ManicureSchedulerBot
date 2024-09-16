package com.anastasiia.manicureschedulerbot.security.filter

import com.anastasiia.manicureschedulerbot.util.TelegramAuthUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

private const val BEARER = "Bearer"

class MiniAppTokenFilter(
    private val botToken: String,
) : OncePerRequestFilter() {

    private val filterLogger = LoggerFactory.getLogger(this::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        if (!isValidAuthHeader(request)) {
            filterChain.doFilter(request, response)
            return
        }

        val rawInitData = getInitDataFromHeader(request)
        if (!TelegramAuthUtil.isValid(rawInitData, botToken)) {
            filterChain.doFilter(request, response)
            return
        }

        val initData = TelegramAuthUtil.parseInitData(rawInitData)
        addUserToSecurityContext(initData["query_id"]!!)
        filterChain.doFilter(request, response)
    }

    private fun isValidAuthHeader(request: HttpServletRequest): Boolean {
        val authHeaderValue = request.getHeader(AUTHORIZATION)
        return authHeaderValue != null && authHeaderValue.startsWith(BEARER)
    }

    private fun addUserToSecurityContext(userId: String) {
        with(SecurityContextHolder.getContext()) {
            authentication = UsernamePasswordAuthenticationToken(userId, null, emptyList())
        }
        filterLogger.debug("User:$userId was added to security context")
    }

    private fun getInitDataFromHeader(request: HttpServletRequest): String {
        val authHeader = request.getHeader(AUTHORIZATION)
        return authHeader.removePrefix("$BEARER ")
    }
}
