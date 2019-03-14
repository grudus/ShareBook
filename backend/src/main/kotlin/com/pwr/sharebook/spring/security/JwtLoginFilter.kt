package com.pwr.sharebook.spring.security

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

internal class JwtLoginFilter(defaultFilterProcessesUrl: String,
                              private val userDetailsService: UserDetailsService,
                              private val passwordEncoder: PasswordEncoder,
                              private val jwtUtils: JwtUtils) : AbstractAuthenticationProcessingFilter(defaultFilterProcessesUrl) {

    private val jwtLogger = LoggerFactory.getLogger(JwtLoginFilter::class.java)

    override fun attemptAuthentication(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse): Authentication {
        val username = httpServletRequest.getParameter(USERNAME_PARAM)
        val password = httpServletRequest.getParameter(PASSWORD_PARAM)

        return Optional.ofNullable(username)
                .filter { a -> !a.isEmpty() }
                .map { userDetailsService.loadUserByUsername(it) }
                .filter { user -> passwordEncoder.matches(password, user.password) }
                .map { user -> UsernamePasswordAuthenticationToken(user, user.password) }
                .orElseThrow<Throwable> { BadCredentialsException("Invalid credentials for user $username") }
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        val username = authResult.name
        jwtLogger.info("User [$username] successfully authenticated")
        jwtUtils.addJwtToResponse(authResult, response)

        SecurityContextHolder.getContext().authentication = authResult
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, failed: AuthenticationException) {
        jwtLogger.warn("Cannot authenticate user [${failed.message}]")
        response.status = HttpServletResponse.SC_UNAUTHORIZED
    }

    companion object {
        private const val USERNAME_PARAM = "email"
        private const val PASSWORD_PARAM = "password"
    }
}
