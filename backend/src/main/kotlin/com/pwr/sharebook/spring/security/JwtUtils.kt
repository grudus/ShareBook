package com.pwr.sharebook.spring.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util.Optional
import java.util.stream.Stream

@Service
class JwtUtils @Autowired
constructor(private val jwtProvider: JwtProvider) {

    fun getJwtFromRequest(request: HttpServletRequest): Optional<String> {
        return Optional.ofNullable(request.cookies)
                .flatMap<Cookie> { cookies -> Stream.of(*cookies).filter { cookie -> cookie.name == AUTHORIZATION_HEADER }.findAny() }
                .map{ it.value }
    }

    fun addJwtToResponse(authentication: Authentication, response: HttpServletResponse) {
        val jwt = jwtProvider.generateJwtToken(authentication)
        response.addCookie(authCookie(jwt))
    }

    private fun authCookie(jwt: String): Cookie {
        val cookie = Cookie(AUTHORIZATION_HEADER, jwt)
        cookie.path = "/"
        cookie.maxAge = AUTH_COOKIE_MAX_AGE
        cookie.isHttpOnly = true
        return cookie
    }


    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val AUTH_COOKIE_MAX_AGE = 60 * 60 * 24 * 10 // 10 days
    }
}
