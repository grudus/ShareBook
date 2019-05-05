package com.pwr.sharebook.group.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<PostEntity, Long> {

    @Query("SELECT p FROM post p JOIN FETCH p.userEntity WHERE p.groupEntity.id = ?1")
    fun findAllForGroupWithCreator(groupId: Long): List<PostEntity>
}