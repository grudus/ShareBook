package com.pwr.sharebook.group

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface GroupRepository: JpaRepository<GroupEntity, Long> {

    @Query("SELECT g FROM class_group g WHERE g.userEntity.id = ?1")
    fun findAllGroupsCreatedByUser(id: Long): List<GroupEntity>

    @Query("SELECT g FROM class_group g JOIN user_group ug ON g.id = ug.userGroupId.groupEntityId WHERE ug.userGroupId.userEntityId = ?1")
    fun findAllGroupsAvailableForUser(id: Long): List<GroupEntity>
}
