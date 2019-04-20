package com.pwr.sharebook.group.usergroup

import com.pwr.sharebook.group.GroupService
import com.pwr.sharebook.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserGroupService
@Autowired
constructor(private val userService: UserService,
            private val groupService: GroupService,
            private val userGroupRepository: UserGroupRepository)
{

    fun addUserToGroup(id: Long, email: String) {
        val user = userService.findByEmailUnsafe(email)
        userGroupRepository.save(UserGroupEntity(UserGroupId(id, user.id), LocalDateTime.now()))
    }

    fun groupWasCreatedByUser(userId: Long, groupId: Long): Boolean =
            groupService.getAllGroupsCreatedByUser(userId).any { it.id == groupId }

    fun userExistsInGroup(userId: Long, groupId: Long): Boolean =
            groupService.findAllUserGroups(userId).any { it.id == groupId }
}
