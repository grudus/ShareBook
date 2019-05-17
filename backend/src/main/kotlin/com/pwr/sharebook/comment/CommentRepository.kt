package com.pwr.sharebook.comment

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<CommentEntity, Long>{

    fun findAllByPostEntityId(postId: Long): List<CommentEntity>
}
