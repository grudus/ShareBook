package com.pwr.sharebook.group

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GroupService
@Autowired
constructor(private val groupRepository: GroupRepository) {

    fun getAllGroups(userId: Long): List<GroupDto> =
            groupRepository.findAllByUserEntityId(userId)
                    .map { GroupDto.fromEntity(it) }
}
