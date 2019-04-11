package com.pwr.sharebook.user.auth

import com.pwr.sharebook.spring.security.JwtUtils
import com.pwr.sharebook.user.UserEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class AuthSecurityUserService {

    fun getCurrentUser(): UserEntity {
        return ((SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken).principal as UserDetailsImpl).user
    }

    fun removeAuthCookie(request: HttpServletRequest, response: HttpServletResponse) {
        request.cookies
                .filter { it.name == JwtUtils.AUTHORIZATION_HEADER }
                .forEach { cookie ->
                    cookie.value = ""
                    cookie.maxAge = 0
                    cookie.path = "/"
                    cookie.isHttpOnly = true
                    response.addCookie(cookie)
                }
    }
}
