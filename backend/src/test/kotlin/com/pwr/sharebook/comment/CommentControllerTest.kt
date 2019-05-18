package com.pwr.sharebook.comment

import com.pwr.sharebook.AuthenticatedControllerTest
import com.pwr.sharebook.group.CreateGroupRequest
import com.pwr.sharebook.group.GroupService
import com.pwr.sharebook.group.post.AddPostRequest
import com.pwr.sharebook.group.post.GroupPostService
import com.pwr.sharebook.utils.RandomUtils.randomText
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class CommentControllerTest: AuthenticatedControllerTest() {

    private fun baseUrl(groupId: Long?, postId: Long?) = "/groups/$groupId/posts/$postId/comments"

    private var groupId: Long? = null
    private var postId: Long? = null

    @Autowired
    private lateinit var groupService: GroupService

    @Autowired
    private lateinit var postService: GroupPostService

    @Before
    fun init() {
        groupId = saveRandomGroup()
        postId = saveRandomPost()
    }

    @Test
    fun shouldReturnEmptyListWhenNoComments() {
        authGet(baseUrl(groupId, postId))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.[*]", empty<String>()))
    }

    @Test
    fun shouldAddCommentToPost() {
        authPost(baseUrl(groupId, postId), randomComment())
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.id", notNullValue()))
    }


    @Test
    fun validationShouldWorkWhenAddingComment() {
        authPost(baseUrl(groupId, postId), randomComment(""))
                .andExpect(status().isBadRequest)
    }


    @Test
    fun shouldGetAllCommentsToThePost() {
        addRandomComment(groupId, postId)
        addRandomComment(groupId, postId)

        flush()

        authGet(baseUrl(groupId, postId))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.[*]", hasSize<Int>(2)))
    }


    @Test
    fun shouldGetCommentsOnlyFromGivenPost() {
        val newPost = saveRandomPost()
        addRandomComment(groupId, postId)
        addRandomComment(groupId, newPost)

        flush()

        authGet(baseUrl(groupId, postId))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.[*]", hasSize<Int>(1)))
    }

    private fun addRandomComment(group: Long? = groupId, post: Long? = postId) {
        authPost(baseUrl(group, post), randomComment())
                .andExpect(status().isCreated)
    }

    private fun saveRandomPost(group: Long = groupId!!) = postService.addPost(AddPostRequest(randomText()), group, currentUser.id!!)
    private fun saveRandomGroup() = groupService.create(CreateGroupRequest(randomText()), currentUser.id!!)
    private fun randomComment(text: String = randomText()) = AddCommentRequest(text)

}
