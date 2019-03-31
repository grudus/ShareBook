package com.pwr.sharebook.group

import com.pwr.sharebook.common.RestKeys
import com.pwr.sharebook.common.RestValidator
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component

@Component
class CreateGroupRequestValidator : RestValidator<CreateGroupRequest>() {

    override fun validate(request: CreateGroupRequest): List<String> {
        return if (StringUtils.isBlank(request.name))
            listOf(RestKeys.EMPTY_NAME)
        else emptyList()
    }
}
