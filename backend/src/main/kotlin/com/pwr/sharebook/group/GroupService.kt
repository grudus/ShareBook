package com.pwr.sharebook.group

import com.pwr.sharebook.common.CannotFindIdException
import com.pwr.sharebook.user.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime.now

@Service
class GroupService
@Autowired
constructor(private val groupRepository: GroupRepository) {

    fun getAllGroups(userId: Long): List<GroupDto> =
            groupRepository.findAllByUserEntityId(userId)
                    .map { GroupDto.fromEntity(it) }

    fun create(request: CreateGroupRequest, userId: Long): Long {
        val entity = GroupEntity(null, request.name, now(), UserEntity(userId))
        groupRepository.save(entity)
        return entity.id ?: throw CannotFindIdException()
    }
}
