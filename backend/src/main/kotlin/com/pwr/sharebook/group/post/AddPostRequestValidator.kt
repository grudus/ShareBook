package com.pwr.sharebook.group.post

import com.pwr.sharebook.common.RestKeys
import com.pwr.sharebook.common.RestValidator
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component

@Component
class AddPostRequestValidator : RestValidator<AddPostRequest>() {

    override fun validate(request: AddPostRequest): List<String> {
        if (StringUtils.isBlank(request.text))
            return listOf(RestKeys.EMPTY_TEXT)
        return emptyList()
    }
}
