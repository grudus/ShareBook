package com.pwr.sharebook.group.post

import com.pwr.sharebook.common.exceptions.CannotObtainIdAfterSaveException
import com.pwr.sharebook.group.GroupEntity
import com.pwr.sharebook.user.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.LocalDateTime.now

@Service
class GroupPostService
@Autowired
constructor(private val postRepository: PostRepository)
{

    fun addPost(addPostRequest: AddPostRequest, groupId: Long, userId: Long): Long {
        val postEntity = PostEntity(null, now(), addPostRequest.text, GroupEntity(groupId), UserEntity(userId))

        postRepository.save(postEntity)

        return postEntity.id ?: throw CannotObtainIdAfterSaveException()
    }

    fun getPostsForGroup(groupId: Long): List<GroupPostDto> {
        return postRepository
                .findAllForGroupWithCreator(groupId)
                .map { GroupPostDto.fromEntity(it, groupId) }
    }

}
