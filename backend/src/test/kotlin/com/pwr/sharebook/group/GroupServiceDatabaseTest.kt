package com.pwr.sharebook.group

import com.pwr.sharebook.AbstractDatabaseTest
import com.pwr.sharebook.group.usergroup.UserGroupService
import com.pwr.sharebook.utils.RandomUtils.randomText
import org.junit.Assert.*
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class GroupServiceDatabaseTest : AbstractDatabaseTest() {

    @Autowired
    private lateinit var groupService: GroupService

    @Autowired
    private lateinit var userGroupService: UserGroupService

    @Test
    fun shouldFindAllUsersForGroup() {
        val user1 = addUser()
        val user2 = addUser()
        addUser()

        val groupId = groupService.create(CreateGroupRequest(randomText()), user1.id!!)
        userGroupService.addUserToGroup(groupId, user2.email!!)

        val users = groupService.findUsersForGroup(groupId)

        assertEquals(2, users.size)
        assertTrue(users.map { it.email }.contains(user1.email!!))
        assertTrue(users.map { it.email }.contains(user2.email!!))
    }

}
