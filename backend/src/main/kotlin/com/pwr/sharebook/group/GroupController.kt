package com.pwr.sharebook.group

import com.pwr.sharebook.user.auth.AuthSecurityUserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/groups")
class GroupController
@Autowired
constructor(private val groupService: GroupService,
            private val authSecurityUserService: AuthSecurityUserService) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun getAllGroups(): List<GroupDto> {
        val userId = authSecurityUserService.getCurrentUserId()
        logger.info("Getting all groups for user [{}]", userId)
        return groupService.getAllGroups(userId)
    }


}
