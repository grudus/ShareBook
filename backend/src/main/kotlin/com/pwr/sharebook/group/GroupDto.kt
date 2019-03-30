package com.pwr.sharebook.group

import com.pwr.sharebook.user.UserIdentification
import java.time.LocalDateTime

data class GroupDto(val id: Long, val name: String, val createdAt: LocalDateTime, val createdBy: UserIdentification) {


    companion object {
        fun fromEntity(group: GroupEntity): GroupDto {
            require(group.id != null) { "Group id cannot be null!" }
            require(group.name != null) { "Group name cannot be null!" }
            require(group.createdAt != null) { "Group createdBy cannot be null!" }
            require(group.userEntity != null) { "Group user entity cannot be null!" }
            return GroupDto(group.id!!, group.name!!, group.createdAt!!,
                    UserIdentification(group.userEntity!!.id!!, group.userEntity.email!!))
        }
    }
}
