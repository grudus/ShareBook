package com.pwr.sharebook.user

import com.pwr.sharebook.user.auth.UserDetailsImpl
import com.pwr.sharebook.user.auth.UserDetailsServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService
@Autowired
constructor(
        private val userDetailsService: UserDetailsServiceImpl,
        private val userRepository: UserRepository) {

    fun findByEmailUnsafe(email: String): UserEntity =
            (userDetailsService.loadUserByUsername(email) as UserDetailsImpl).user

    fun findByEmail(email: String): UserEntity? =
            try {
                findByEmailUnsafe(email)
            } catch (e: UsernameNotFoundException) {
                null
            }

    fun findById(userCreatorId: Long): UserEntity? = userRepository.findByIdOrNull(userCreatorId)
}
