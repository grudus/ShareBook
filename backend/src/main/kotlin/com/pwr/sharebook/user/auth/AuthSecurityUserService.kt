package com.pwr.sharebook.user.auth

import com.pwr.sharebook.user.UserEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthSecurityUserService {

    fun getCurrentUser(): UserEntity {
        return ((SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken).principal as UserDetailsImpl).user
    }
}
