package com.pwr.sharebook.user

import com.pwr.sharebook.common.RestKeys
import com.pwr.sharebook.common.RestValidator
import com.pwr.sharebook.common.validator.EmailValidator
import com.pwr.sharebook.user.auth.AuthSecurityUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UpdateUserRequestValidator
@Autowired
constructor(private val authSecurityUserService: AuthSecurityUserService,
            private val emailValidator: EmailValidator) : RestValidator<UserDto>() {


    override fun validate(request: UserDto): List<String> {
        val currentUser = authSecurityUserService.getCurrentUser()

        if (currentUser.id != request.id)
            return listOf(RestKeys.UNAUTHORIZED)

        return if (currentUser.email == request.email)
            listOf()
        else checkEmail(request)
    }

    private fun checkEmail(request: UserDto): List<String> {
        val emailErrors = emailValidator.validateEmail(request.email)

        return if (emailErrors.isNotEmpty()) {
            emailErrors
        } else listOf()
    }
}
