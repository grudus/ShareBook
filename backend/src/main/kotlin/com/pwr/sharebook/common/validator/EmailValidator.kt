package com.pwr.sharebook.common.validator

import com.pwr.sharebook.common.RegexUtils
import com.pwr.sharebook.common.RestKeys
import com.pwr.sharebook.user.registration.UserRegistrationService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class EmailValidator
@Autowired
constructor(private val userRegistrationService: UserRegistrationService) {

    fun validateEmail(email: String): List<String> =
            if (StringUtils.isBlank(email))
                listOf(RestKeys.EMPTY_EMAIL)
            else if (!RegexUtils.EMAIL_REGEX.matches(email))
                listOf(RestKeys.INVALID_EMAIL)
            else if (userRegistrationService.emailExists(email))
                listOf(RestKeys.EMAIL_EXISTS)
            else emptyList()
}
