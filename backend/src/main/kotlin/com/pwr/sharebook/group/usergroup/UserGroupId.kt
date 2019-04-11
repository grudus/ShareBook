package com.pwr.sharebook.group.usergroup

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class UserGroupId(
        @Column(name = "class_group_id")
        val groupEntityId: Long?,

        @Column(name = "user_id")
        val userEntityId: Long?

) : Serializable {
    constructor() : this(null, null)
}
