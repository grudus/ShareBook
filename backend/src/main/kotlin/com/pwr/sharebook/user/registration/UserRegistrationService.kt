package com.pwr.sharebook.user.registration

import com.pwr.sharebook.user.UserEntity
import com.pwr.sharebook.user.UserRepository
import com.pwr.sharebook.user.role.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
@Transactional
class UserRegistrationService
@Autowired
constructor(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val roleService: RoleService
        )
{

    fun createUser(createUserRequest: CreateUserRequest): Long {
        val user = mapCreateUserRequestToUserEntity(createUserRequest)
        userRepository.save(user)
        return user.id ?: throw IllegalStateException("Cannot obtain newly created user id")
    }

    fun emailExists(email: String): Boolean = userRepository.existsByEmail(email)


    private fun mapCreateUserRequestToUserEntity(request: CreateUserRequest) =
            UserEntity(
                    null,
                    request.email,
                    passwordEncoder.encode(request.password),
                    null,
                    request.firstName,
                    request.lastName,
                    roleService.getDefaultRole()
            )

}
