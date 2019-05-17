package com.pwr.sharebook.comment

import com.pwr.sharebook.common.exceptions.CannotObtainIdAfterSaveException
import com.pwr.sharebook.group.post.PostEntity
import com.pwr.sharebook.user.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import javax.transaction.Transactional

@Service
@Transactional
class CommentService
@Autowired
constructor(private val commentRepository: CommentRepository) {

    fun addComment(addCommentRequest: AddCommentRequest, postId: Long, userId: Long): Long {
        val comment = CommentEntity(null, now(), addCommentRequest.text, PostEntity(postId), UserEntity(userId))
        commentRepository.save(comment)
        return comment.id ?: throw CannotObtainIdAfterSaveException()
    }

    fun findCommentsForPost(postId: Long): List<CommentDto> =
            commentRepository.findAllByPostEntityId(postId)
                    .map { CommentDto.fromEntity(it) }
}
