package com.pwr.sharebook.group

import com.pwr.sharebook.common.IdResponse
import com.pwr.sharebook.user.AuthenticatedUser
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/groups")
class GroupController
@Autowired
constructor(private val groupService: GroupService,
            private val createGroupRequestValidator: CreateGroupRequestValidator
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun getAllGroups(user: AuthenticatedUser): List<GroupDto> {
        logger.info("Getting all groups for user [{}]", user.email)
        return groupService.getAllGroups(user.id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createGroup(@Valid @RequestBody createGroupRequest: CreateGroupRequest, user: AuthenticatedUser): IdResponse {
        logger.info("User [{}] is creating new group:", user.email, createGroupRequest)
        return IdResponse(groupService.create(createGroupRequest, user.id))
    }


    @InitBinder("createGroupRequest")
    protected fun initEditBinder(binder: WebDataBinder) {
        binder.validator = createGroupRequestValidator
    }

}
