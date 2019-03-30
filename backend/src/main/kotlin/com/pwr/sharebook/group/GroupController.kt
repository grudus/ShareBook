package com.pwr.sharebook.group

import com.pwr.sharebook.common.IdResponse
import com.pwr.sharebook.user.auth.AuthSecurityUserService
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
            private val authSecurityUserService: AuthSecurityUserService,
            private val createGroupRequestValidator: CreateGroupRequestValidator

) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun getAllGroups(): List<GroupDto> {
        val userId = authSecurityUserService.getCurrentUserId()
        logger.info("Getting all groups for user [{}]", userId)
        return groupService.getAllGroups(userId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createGroup(@Valid @RequestBody createGroupRequest: CreateGroupRequest): IdResponse {
        val userId = authSecurityUserService.getCurrentUserId()
        logger.info("User [{}] is creating new group:", userId, createGroupRequest)
        return IdResponse(groupService.create(createGroupRequest, userId))
    }


    @InitBinder("createGroupRequest")
    protected fun initEditBinder(binder: WebDataBinder) {
        binder.validator = createGroupRequestValidator
    }

}
