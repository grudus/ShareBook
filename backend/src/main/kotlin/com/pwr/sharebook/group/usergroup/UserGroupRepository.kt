package com.pwr.sharebook.group.usergroup;

import com.pwr.sharebook.user.UserDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface UserGroupRepository : JpaRepository<UserGroupEntity, UserGroupId> {

    @Query("SELECT new com.pwr.sharebook.user.UserDto(user.id, user.email, user.firstName, user.lastName, user.avatarUrl) " +
            "FROM user_group userGroup JOIN user user ON user.id = userGroup.userGroupId.userEntityId " +
            "WHERE userGroup.userGroupId.groupEntityId = ?1")
    fun findUsersForGroup(groupId: Long): List<UserDto>
}
