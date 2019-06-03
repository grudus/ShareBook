package com.pwr.sharebook.user.registration

import com.pwr.sharebook.common.RestKeys
import com.pwr.sharebook.common.RestValidator
import com.pwr.sharebook.common.validator.EmailValidator
import org.apache.commons.lang3.StringUtils.isBlank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RegistrationValidator
@Autowired
constructor(private val emailValidator: EmailValidator) : RestValidator<CreateUserRequest>() {


    override fun validate(request: CreateUserRequest): List<String> =
            validatePassword(request) + validateName(request) + emailValidator.validateEmail(request.email)


    private fun validatePassword(request: CreateUserRequest): List<String> =
            if (isBlank(request.password) || isBlank(request.confirmPassword))
                listOf(RestKeys.EMPTY_PASSWORD)
            else if (request.password != request.confirmPassword)
                listOf(RestKeys.PASSWORD_NOT_MATCHES)
            else emptyList()


    private fun validateName(request: CreateUserRequest): List<String> =
            if (isBlank(request.firstName) || isBlank(request.lastName))
                listOf(RestKeys.EMPTY_NAME)
            else emptyList()
}
