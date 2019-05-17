package com.pwr.sharebook.comment

import com.pwr.sharebook.common.RestKeys
import com.pwr.sharebook.common.RestValidator
import org.apache.commons.lang3.StringUtils.isBlank
import org.springframework.stereotype.Component

@Component
class AddCommentRequestValidator: RestValidator<AddCommentRequest>() {

    override fun validate(request: AddCommentRequest): List<String> {
        return if (isBlank(request.text))
            listOf(RestKeys.EMPTY_TEXT)
        else emptyList()
    }
}
