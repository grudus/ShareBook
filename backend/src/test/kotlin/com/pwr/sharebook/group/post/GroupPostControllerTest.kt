package com.pwr.sharebook.group.post

import com.pwr.sharebook.AuthenticatedControllerTest
import com.pwr.sharebook.comment.AddCommentRequest
import com.pwr.sharebook.comment.CommentService
import com.pwr.sharebook.common.IdResponse
import com.pwr.sharebook.group.CreateGroupRequest
import com.pwr.sharebook.group.GroupService
import com.pwr.sharebook.utils.RandomUtils.randomText
import org.hamcrest.Matchers.hasSize
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class GroupPostControllerTest : AuthenticatedControllerTest() {

    @Autowired
    private lateinit var groupService: GroupService

    @Autowired
    private lateinit var commentService: CommentService

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
    fun shouldGetAllPostsForGroup() {
        authPost("/groups/$groupId/posts", randomPost())
        authPost("/groups/$groupId/posts", randomPost())
        authPost("/groups/$groupId/posts", randomPost())

        val newGroupId = groupService.create(CreateGroupRequest(randomText()), currentUser.id!!)

        authPost("/groups/$newGroupId/posts", randomPost())

        flush()

        authGet("/groups/$groupId/posts")
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.[*]", hasSize<Int>(3)))
    }

    @Test
    fun shouldUseValidationWhenCreatingPost() {
        authPost("/groups/$groupId/posts", randomPost("\t"))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun shouldGetPostsWithAndWithoutComments() {
        authPost("/groups/$groupId/posts", randomPost())

        val response = authPost("/groups/$groupId/posts", randomPost())
                .andReturn().response.contentAsString

        val id = mapResponse(response, IdResponse::class.java).id


        commentService.addComment(AddCommentRequest(randomText()), id, currentUser.id!!)

        flush()

        authGet("/groups/$groupId/posts?withComments=true")
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.[*]", hasSize<Int>(2)))
                .andExpect(jsonPath("$.[0].post", notNullValue()))
                .andExpect(jsonPath("$.[0].comments", hasSize<Int>(0)))
                .andExpect(jsonPath("$.[1].comments", hasSize<Int>(1)))

    }

    private fun randomPost(text: String = randomText()) = AddPostRequest(text)

}
