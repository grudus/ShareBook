package com.pwr.sharebook.group.post

import com.pwr.sharebook.user.UserDto
import java.time.LocalDateTime

data class GroupPostDto(
        val text: String,
        val groupId: Long,
        val postId: Long,
        val createdBy: UserDto,
        val createdAt: LocalDateTime,
        val attachmentId: String? = null
        ) {

    companion object {
        fun fromEntity(post: PostEntity, groupId: Long): GroupPostDto {
            require(post.id != null) { "Post id cannot be null!" }
            require(post.text != null) { "Post text cannot be null!" }
            require(post.createdAt != null) { "Post createdBy cannot be null!" }
            require(post.userEntity != null) { "Post user entity cannot be null!" }
            return GroupPostDto(post.text, groupId, post.id, UserDto.fromEntity(post.userEntity), post.createdAt,
                    post.attachments?.getOrNull(0)?.location)
        }
    }

}
