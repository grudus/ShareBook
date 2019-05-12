package com.pwr.sharebook.group.usergroup

import com.pwr.sharebook.notification.event.NotificationEventPublisher
import com.pwr.sharebook.user.AuthenticatedUser
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/groups")
class AddUserToGroupController
@Autowired
constructor(private val userGroupService: UserGroupService,
            private val addUserToGroupRequestValidator: AddUserToGroupRequestValidator,
            private val notificationEventPublisher: NotificationEventPublisher) {

    private val logger = LoggerFactory.getLogger(javaClass)


    @PutMapping("/add-user")
    fun addUserToGroup(@RequestBody @Valid addUserToGroupRequest: AddUserToGroupRequest,
                       user: AuthenticatedUser) {
        logger.info("Adding user [{}] to group {}", addUserToGroupRequest.email, addUserToGroupRequest.groupId)
        userGroupService.addUserToGroup(addUserToGroupRequest.groupId, addUserToGroupRequest.email)
        notificationEventPublisher.publishUserAddedToGroup(addUserToGroupRequest, user.id)
    }


    @InitBinder("addUserToGroupRequest")
    protected fun initAddUserToGroupBinder(binder: WebDataBinder) {
        binder.validator = addUserToGroupRequestValidator
    }
}
