package com.pwr.sharebook.user.registration

import com.pwr.sharebook.common.RegexUtils.EMAIL_REGEX
import com.pwr.sharebook.common.RestKeys
import org.apache.commons.lang3.StringUtils.isBlank
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class RegistrationValidator
@Autowired
constructor(private val userRegistrationService: UserRegistrationService) : Validator {

    override fun validate(requestObject: Any, errors: Errors) {
        val request = requestObject as CreateUserRequest

        validateEmail(request, errors)
        validateName(request, errors)
        validatePassword(request, errors)

    }


    private fun validatePassword(request: CreateUserRequest, errors: Errors) {
        if (isBlank(request.password) || isBlank(request.confirmPassword))
            errors.reject(RestKeys.EMPTY_PASSWORD)
        else if (request.password != request.confirmPassword)
            errors.reject(RestKeys.PASSWORD_NOT_MATCHES)
    }

    private fun validateName(request: CreateUserRequest, errors: Errors) {
        if (isBlank(request.firstName) || isBlank(request.lastName))
            errors.reject(RestKeys.EMPTY_NAME)
    }

    private fun validateEmail(request: CreateUserRequest, errors: Errors) {
        if (isBlank(request.email))
            errors.reject(RestKeys.EMPTY_EMAIL)
        else if (!EMAIL_REGEX.matches(request.email))
            errors.reject(RestKeys.INVALID_EMAIL)
        else if (userRegistrationService.emailExists(request.email))
            errors.reject(RestKeys.EMAIL_EXISTS)
    }

    override fun supports(clazz: Class<*>): Boolean =
            CreateUserRequest::class.java.isAssignableFrom(clazz)

}
