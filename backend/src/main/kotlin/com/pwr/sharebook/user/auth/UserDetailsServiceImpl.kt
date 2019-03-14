package com.pwr.sharebook.user.auth

import com.pwr.sharebook.user.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl

@Autowired
constructor(private val userRepository: UserRepository) : UserDetailsService {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun loadUserByUsername(email: String): UserDetails {
        logger.info("Loading by username [{}]", email)

        return userRepository.findByEmail(email)
                ?.let { UserDetailsImpl(it) }
                ?: throw UsernameNotFoundException("Cannot find user with email: $email")
    }

}
