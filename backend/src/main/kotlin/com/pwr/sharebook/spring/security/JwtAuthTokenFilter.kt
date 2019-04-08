package com.pwr.sharebook.spring.security

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

internal class JwtAuthTokenFilter(private val jwtProvider: JwtProvider,
                                  private val userDetailsService: UserDetailsService,
                                  private val jwtUtils: JwtUtils) : OncePerRequestFilter() {
    private val jwtLogger = LoggerFactory.getLogger(JwtAuthTokenFilter::class.java)

    private val whiteListRegex = Regex(".*auth.*")

    override fun doFilterInternal(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, filterChain: FilterChain) {
        if (httpServletRequest.requestURI.matches(whiteListRegex)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return
        }

        val authentication = jwtUtils.getJwtFromRequest(httpServletRequest)
                .filter { jwtProvider.validateJwtToken(it) }
                .map { jwtProvider.getUserNameFromJwtToken(it) }
                .map { userDetailsService.loadUserByUsername(it) }
                .map { user -> usernameToken(user, httpServletRequest) }
                .orElse(null)

        jwtLogger.debug("Get authentication for user: $authentication")
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(httpServletRequest, httpServletResponse)
    }

    private fun usernameToken(user: UserDetails, request: HttpServletRequest): UsernamePasswordAuthenticationToken {
        val token = UsernamePasswordAuthenticationToken(user, null, user.authorities)
        token.details = WebAuthenticationDetailsSource().buildDetails(request)
        return token
    }
}
