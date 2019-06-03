package com.pwr.sharebook.user

import com.pwr.sharebook.common.exceptions.CannotFindIdException
import com.pwr.sharebook.user.auth.AuthSecurityUserService
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
        private val userRepository: UserRepository,
        private val authSecurityUserService: AuthSecurityUserService) {

    fun findByEmailUnsafe(email: String): UserEntity =
            (userDetailsService.loadUserByUsername(email) as UserDetailsImpl).user

    fun findByEmail(email: String): UserEntity? =
            try {
                findByEmailUnsafe(email)
            } catch (e: UsernameNotFoundException) {
                null
            }

    fun findById(userCreatorId: Long): UserEntity? = userRepository.findByIdOrNull(userCreatorId)

    fun getCurrentUser(): UserDto =
            UserDto.fromEntity(
                    authSecurityUserService.getCurrentUser()
            )

    fun update(request: UserDto): UserDto {
        val user: UserEntity = userRepository.findById(request.id).orElseThrow { CannotFindIdException() }

        user.email = request.email
        user.firstName = request.firstName
        user.lastName = request.lastName
        user.avatarUrl = request.avatarUrl

        userRepository.save(user)
        return UserDto.fromEntity(user)
    }
}
