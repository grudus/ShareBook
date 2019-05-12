package com.pwr.sharebook.notification

import com.pwr.sharebook.group.GroupDto
import com.pwr.sharebook.group.GroupEntity
import com.pwr.sharebook.user.UserIdentification
import java.time.LocalDateTime

data class NotificationDto(val id: Long,
                           val title: String,
                           val text: String,
                           val linkHref: String,
                           val visited: Boolean,
                           val createdAt: LocalDateTime) {


    companion object {
        fun fromEntity(group: GroupEntity): GroupDto {
            require(group.id != null) { "Group id cannot be null!" }
            require(group.name != null) { "Group name cannot be null!" }
            require(group.createdAt != null) { "Group createdBy cannot be null!" }
            require(group.userEntity != null) { "Group user entity cannot be null!" }
            return GroupDto(group.id, group.name, group.photoUrl, group.createdAt,
                    UserIdentification(group.userEntity.id!!, group.userEntity.email!!))
        }

        fun fromEntity(entity: NotificationEntity): NotificationDto {
            require(entity.id != null) { "Notification id cannot be null!" }
            require(entity.title != null) { "Notification title cannot be null!" }
            require(entity.text != null) { "Notification text cannot be null!" }
            require(entity.linkHref != null) { "Notification linkHref  cannot be null!" }
            require(entity.visited != null) { "Notification visited  cannot be null!" }
            require(entity.createdAt != null) { "Notification createdAt cannot be null!" }

            return NotificationDto(
                    entity.id,
                    entity.title,
                    entity.text,
                    entity.linkHref,
                    entity.visited,
                    entity.createdAt
            )
        }
    }
}
