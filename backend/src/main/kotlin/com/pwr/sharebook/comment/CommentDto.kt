package com.pwr.sharebook.comment

import com.pwr.sharebook.user.UserDto
import java.time.LocalDateTime

data class CommentDto(
        val id: Long,
        val text: String,
        val cratedAt: LocalDateTime,
        val createdBy: UserDto,
        val postId: Long
) {

    companion object {
        fun fromEntity(entity: CommentEntity): CommentDto {
            require(entity.id != null) { "Comment id cannot be null!" }
            require(entity.text != null) { "Comment text cannot be null!" }
            require(entity.createdAt != null) { "Comment createdat cannot be null!" }
            require(entity.createdBy != null) { "Comment created by cannot be null!" }
            require(entity.postEntity != null) { "Comment post entity cannot be null!" }
            require(entity.postEntity.id != null) { "Comment post id cannot be null!" }
            return CommentDto(entity.id, entity.text, entity.createdAt, UserDto.fromEntity(entity.createdBy),
                    entity.postEntity.id)
        }
    }

}
