package com.pwr.sharebook.group.post

import com.pwr.sharebook.attachments.AttachmentEntity
import com.pwr.sharebook.attachments.AttachmentService
import com.pwr.sharebook.comment.CommentDto
import com.pwr.sharebook.common.exceptions.CannotObtainIdAfterSaveException
import com.pwr.sharebook.group.GroupEntity
import com.pwr.sharebook.user.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.time.LocalDateTime.now

@Service
class GroupPostService
@Autowired
constructor(
        private val postRepository: PostRepository,
        private val attachmentService: AttachmentService
) {

    @CacheEvict(value = ["posts"], key = "#groupId")
    fun addPost(addPostRequest: AddPostRequest, groupId: Long, userId: Long): Long {
        val attachment: AttachmentEntity? = addPostRequest.attachmentId?.let { attachmentService.findAttachmentByLocation(it) }
        val postEntity = PostEntity(null, now(), addPostRequest.text, GroupEntity(groupId), UserEntity(userId), null, if (attachment == null) null else listOf(attachment))

        postRepository.save(postEntity)

        return postEntity.id ?: throw CannotObtainIdAfterSaveException()
    }

    fun getPostsForGroup(groupId: Long): List<GroupPostDto> {
        return postRepository
                .findAllForGroupWithCreator(groupId)
                .map { GroupPostDto.fromEntity(it, groupId) }
    }


    @Cacheable(value = ["posts"], key = "#groupId")
    fun getPostsWithComments(groupId: Long): List<PostWithCommentsDto> =
            postRepository
                    .findAllPostsForGroupWithComments(groupId)
                    .groupBy { it.id }
                    .map { (_, post) ->
                        val groupPost = GroupPostDto.fromEntity(post[0], groupId)
                        val comments = post[0].comments?.map { CommentDto.fromEntity(it) } ?: listOf()
                        PostWithCommentsDto(groupPost, comments)
                    }
}
