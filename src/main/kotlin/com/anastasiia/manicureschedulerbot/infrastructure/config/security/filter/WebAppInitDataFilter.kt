package com.anastasiia.manicureschedulerbot.infrastructure.config.security.filter

import com.anastasiia.manicureschedulerbot.infrastructure.config.security.util.WebAppInitDataVerificationResult.SuccessWebAppInitDataVerificationResult
import com.anastasiia.manicureschedulerbot.infrastructure.config.security.util.WebAppInitDataVerifier
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class WebAppInitDataFilter(
    private val botToken: String,
) : OncePerRequestFilter() {
    private val filterLogger = LoggerFactory.getLogger(this::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val webAppInitData = when (val result = WebAppInitDataVerifier.verify(request, botToken)) {
            is SuccessWebAppInitDataVerificationResult -> result.webAppInitData
            else -> null
        }
        if (webAppInitData == null) {
            filterChain.doFilter(request, response)
            return
        }
        addUserToSecurityContext(webAppInitData["query_id"]!!)
        filterChain.doFilter(request, response)
    }

    private fun addUserToSecurityContext(userId: String) {
        with(SecurityContextHolder.getContext()) {
            authentication = UsernamePasswordAuthenticationToken(userId, null, emptyList())
        }
        filterLogger.debug("User:$userId was added to security context")
    }
}
