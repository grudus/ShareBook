package com.pwr.sharebook.group.post

import com.pwr.sharebook.AuthenticatedControllerTest
import com.pwr.sharebook.group.CreateGroupRequest
import com.pwr.sharebook.group.GroupService
import com.pwr.sharebook.utils.RandomUtils.randomText
import org.hamcrest.Matchers.notNullValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class GroupPostControllerTest : AuthenticatedControllerTest() {

    @Autowired
    private lateinit var groupService: GroupService

    private var groupId: Long? = null

    @Before
    fun init() {
        groupId = groupService.create(CreateGroupRequest(randomText()), currentUser.id!!)
    }

    @Test
    fun shouldBeAbleToAddPostAndGetId() {
        authPost("/groups/$groupId/posts", randomPost())
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.id", notNullValue()))
    }

    @Test
    fun shouldUseValidationWhenCreatingPost() {
        authPost("/groups/$groupId/posts", randomPost("\t"))
                .andExpect(status().isBadRequest)
    }

    private fun randomPost(text: String = randomText()) = AddPostRequest(text)

}
