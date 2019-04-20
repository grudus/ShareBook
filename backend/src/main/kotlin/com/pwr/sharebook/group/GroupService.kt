package com.pwr.sharebook.group

import com.pwr.sharebook.common.exceptions.CannotObtainIdAfterSaveException
import com.pwr.sharebook.group.usergroup.UserGroupEntity
import com.pwr.sharebook.group.usergroup.UserGroupId
import com.pwr.sharebook.group.usergroup.UserGroupRepository
import com.pwr.sharebook.user.UserEntity
import com.pwr.sharebook.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime.now

@Service
class GroupService
@Autowired
constructor(
        private val groupRepository: GroupRepository,
        private val userGroupRepository: UserGroupRepository,
        private val userService: UserService
) {

    fun getAllGroupsCreatedByUser(userId: Long): List<GroupDto> =
            groupRepository.findAllGroupsCreatedByUser(userId)
                    .map { GroupDto.fromEntity(it) }

    fun create(request: CreateGroupRequest, userId: Long): Long {
        val entity = GroupEntity(null, request.name, request.photoUrl, now(), UserEntity(userId))
        groupRepository.save(entity)
        userGroupRepository.save(UserGroupEntity(UserGroupId(entity.id, userId), now()))
        return entity.id ?: throw CannotObtainIdAfterSaveException()
    }

    fun findAllUserGroups(userId: Long): List<GroupDto> {
        return groupRepository.findAllGroupsAvailableForUser(userId)
                .map(GroupDto.Companion::fromEntity)
    }

    fun addUserToGroup(id: Long, email: String) {
        val user = userService.findByEmailUnsafe(email)
        userGroupRepository.save(UserGroupEntity(UserGroupId(id, user.id), now()))
    }

    fun groupWasCreatedByUser(userId: Long, groupId: Long): Boolean =
            getAllGroupsCreatedByUser(userId).any { it.id == groupId }

    fun userExistsInGroup(userId: Long, groupId: Long): Boolean =
            findAllUserGroups(userId).any { it.id == groupId }
}
