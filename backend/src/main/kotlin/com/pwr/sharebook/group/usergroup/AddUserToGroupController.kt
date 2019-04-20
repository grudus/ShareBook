package com.pwr.sharebook.group.usergroup

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
            private val addUserToGroupRequestValidator: AddUserToGroupRequestValidator) {

    private val logger = LoggerFactory.getLogger(javaClass)


    @PutMapping("/add-user")
    fun addUserToGroup(@RequestBody @Valid addUserToGroupRequest: AddUserToGroupRequest) {
        logger.info("Adding user [{}] to group {}", addUserToGroupRequest.email, addUserToGroupRequest.groupId)
        userGroupService.addUserToGroup(addUserToGroupRequest.groupId, addUserToGroupRequest.email)
    }


    @InitBinder("addUserToGroupRequest")
    protected fun initAddUserToGroupBinder(binder: WebDataBinder) {
        binder.validator = addUserToGroupRequestValidator
    }
}
