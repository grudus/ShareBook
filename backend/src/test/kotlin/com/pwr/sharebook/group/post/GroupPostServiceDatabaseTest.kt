package com.pwr.sharebook.group.post

import com.pwr.sharebook.AbstractDatabaseTest
import com.pwr.sharebook.group.CreateGroupRequest
import com.pwr.sharebook.group.GroupService
import com.pwr.sharebook.user.UserEntity
import com.pwr.sharebook.utils.RandomUtils.randomText
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.assertEquals

class GroupPostServiceDatabaseTest: AbstractDatabaseTest() {

    @Autowired
    private lateinit var groupPostService: GroupPostService

    @Autowired
    private lateinit var groupPostRepository: PostRepository

    @Autowired
    private lateinit var groupService: GroupService

    private lateinit var user: UserEntity

    @Before
    fun init() {
        user = addUser()
    }

    @Test
    fun shouldAddPost() {
        val groupId = groupService.create(CreateGroupRequest(randomText()), user.id!!)

        groupPostService.addPost(randomPost(), groupId, user.id!!)
        groupPostService.addPost(randomPost(), groupId, user.id!!)

        val count = groupPostRepository.count()

        assertEquals(2, count)
    }

    private fun randomPost() = AddPostRequest(randomText())

}
