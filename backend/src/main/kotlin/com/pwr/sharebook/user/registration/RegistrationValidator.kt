package com.pwr.sharebook.user.registration

import com.pwr.sharebook.common.RegexUtils.EMAIL_REGEX
import com.pwr.sharebook.common.RestKeys
import com.pwr.sharebook.common.RestValidator
import org.apache.commons.lang3.StringUtils.isBlank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class RegistrationValidator
@Autowired
constructor(private val userRegistrationService: UserRegistrationService) : RestValidator<CreateUserRequest>() {


    override fun validate(request: CreateUserRequest): List<String> =
            validatePassword(request) + validateName(request) + validateEmail(request)


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


    private fun validateEmail(request: CreateUserRequest): List<String> =
            if (isBlank(request.email))
                listOf(RestKeys.EMPTY_EMAIL)
            else if (!EMAIL_REGEX.matches(request.email))
                listOf(RestKeys.INVALID_EMAIL)
            else if (userRegistrationService.emailExists(request.email))
                listOf(RestKeys.EMAIL_EXISTS)
            else emptyList()


}
