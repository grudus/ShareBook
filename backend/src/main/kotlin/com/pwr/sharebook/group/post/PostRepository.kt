package com.pwr.sharebook.group.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface PostRepository: JpaRepository<PostEntity, Long> {

    @Query("SELECT p FROM post p JOIN FETCH p.userEntity WHERE p.groupEntity.id = ?1")
    fun findAllForGroupWithCreator(groupId: Long): List<PostEntity>

    @Query("SELECT p FROM post p JOIN FETCH p.userEntity LEFT JOIN FETCH p.comments WHERE p.groupEntity.id = ?1")
    fun findAllPostsForGroupWithComments(groupId: Long): List<PostEntity>

    fun findAllByUserEntityIdAndCreatedAtBetween(userId: Long, dateFrom: LocalDateTime?, dateTo: LocalDateTime?): List<PostEntity>
}
