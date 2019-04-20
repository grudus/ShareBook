package com.pwr.sharebook.group.usergroup

import com.pwr.sharebook.common.RegexUtils
import com.pwr.sharebook.common.RestKeys
import com.pwr.sharebook.common.RestValidator
import com.pwr.sharebook.group.GroupService
import com.pwr.sharebook.user.UserService
import com.pwr.sharebook.user.auth.AuthSecurityUserService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AddUserToGroupRequestValidator
@Autowired
constructor(
        private val userService: UserService,
        private val groupService: GroupService,
        private val authSecurityUserService: AuthSecurityUserService
) : RestValidator<AddUserToGroupRequest>() {

    override fun validate(request: AddUserToGroupRequest): List<String> {
        if (StringUtils.isBlank(request.email)) {
            return listOf(RestKeys.EMPTY_EMAIL)
        }

        if (!RegexUtils.EMAIL_REGEX.matches(request.email)) {
            return listOf(RestKeys.INVALID_EMAIL)
        }


        if (!groupService.groupWasCreatedByUser(authSecurityUserService.getCurrentUserId(), request.groupId)) {
            return listOf(RestKeys.UNAUTHORIZED)
        }

        val userToAdd = userService.findByEmail(request.email)

        @Suppress("FoldInitializerAndIfToElvis")
        if (userToAdd == null) {
            return listOf(RestKeys.USER_DOES_NOT_EXISTS)
        }

        if (groupService.userExistsInGroup(userToAdd.id!!, request.groupId)) {
            return listOf(RestKeys.USER_ALREADY_EXISTS_IN_GROUP)
        }

        return emptyList()
    }

}
