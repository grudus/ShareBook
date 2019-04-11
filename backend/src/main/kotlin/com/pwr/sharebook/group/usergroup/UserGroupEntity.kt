package com.pwr.sharebook.group.usergroup

import java.time.LocalDateTime
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "user_group")
@Entity(name = "user_group")
class UserGroupEntity(
        @EmbeddedId
        val userGroupId: UserGroupId?,
        val joinDate: LocalDateTime?
) {
    constructor() : this(null, null)
}
