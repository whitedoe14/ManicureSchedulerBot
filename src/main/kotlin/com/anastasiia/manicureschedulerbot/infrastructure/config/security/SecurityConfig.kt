package com.anastasiia.manicureschedulerbot.infrastructure.config.security

import com.anastasiia.manicureschedulerbot.infrastructure.config.security.filter.MiniAppTokenFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.AuthorizationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(
        http: HttpSecurity,
        @Value("\${telegram.bot-token}") botToken: String,
    ): SecurityFilterChain {
        http
            .csrf { csrf ->
                csrf.disable()
            }
            .httpBasic { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/book").permitAll()
                    // permit access for css, javascript files
                    .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**").permitAll()
                    .requestMatchers("/images/**", "/vendor/**", "/fonts/**").permitAll()
                    .requestMatchers("/submit-book").authenticated()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(MiniAppTokenFilter(botToken), AuthorizationFilter::class.java)
        return http.build()
    }
}
