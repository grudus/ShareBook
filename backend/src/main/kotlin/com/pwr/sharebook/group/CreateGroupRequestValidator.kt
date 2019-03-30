package com.pwr.sharebook.group

import com.pwr.sharebook.common.RestKeys
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class CreateGroupRequestValidator: Validator {
    override fun validate(target: Any, errors: Errors) {
        val request = target as CreateGroupRequest

        if (StringUtils.isBlank(request.name)) {
            errors.reject(RestKeys.EMPTY_NAME)
        }
    }

    override fun supports(clazz: Class<*>): Boolean =
            clazz.isAssignableFrom(CreateGroupRequest::class.java)
}
