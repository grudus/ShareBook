package com.pwr.sharebook.group

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface GroupRepository: JpaRepository<GroupEntity, Long> {

    @Query("SELECT g FROM class_group g WHERE g.userEntity.id = ?1")
    fun findAllByUserEntityId(id: Long): List<GroupEntity>
}
