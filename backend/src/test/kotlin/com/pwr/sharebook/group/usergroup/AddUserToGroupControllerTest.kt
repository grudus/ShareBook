package com.pwr.sharebook.group.usergroup

import com.pwr.sharebook.AuthenticatedControllerTest
import com.pwr.sharebook.group.CreateGroupRequest
import com.pwr.sharebook.group.GroupService
import com.pwr.sharebook.utils.RandomUtils.randomText
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertTrue

class AddUserToGroupControllerTest: AuthenticatedControllerTest() {

    private val BASE_URL = "/groups"

    @Autowired
    private lateinit var groupService: GroupService

    @Test
    fun shouldAddUserToGroup() {
        val prevCurrentEmail = currentUser.email!!
        val prevCurrentId = currentUser.id!!

        logInAnotherUser()

        val groupId = groupService.create(CreateGroupRequest(randomText()), currentUser.id!!)

        flush()

        authPut("$BASE_URL/add-user", AddUserToGroupRequest(prevCurrentEmail, groupId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk)

        val userGroups = groupService.findAllUserGroups(prevCurrentId)

        assertTrue(userGroups.isNotEmpty())
    }

    @Test
    fun shouldNotBeAbleToAddUserToGroupDueToValidator() {
        val prevCurrentId = currentUser.id!!

        logInAnotherUser()

        val groupId = groupService.create(CreateGroupRequest(randomText()), currentUser.id!!)

        flush()

        authPut("$BASE_URL/add-user", AddUserToGroupRequest(randomText(), groupId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest)

        val userGroups = groupService.findAllUserGroups(prevCurrentId)

        assertTrue(userGroups.isEmpty())

    }

}
