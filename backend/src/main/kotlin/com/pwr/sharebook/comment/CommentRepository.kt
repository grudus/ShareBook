package com.pwr.sharebook.comment

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface CommentRepository : JpaRepository<CommentEntity, Long> {

    fun findAllByPostEntityId(postId: Long): List<CommentEntity>
    fun findAllByCreatedByIdAndCreatedAtBetween(userId: Long, dateFrom: LocalDateTime, dateTo: LocalDateTime): List<CommentEntity>
}
