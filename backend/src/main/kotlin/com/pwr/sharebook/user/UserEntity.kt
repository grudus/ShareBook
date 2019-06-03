package com.pwr.sharebook.user

import com.pwr.sharebook.user.role.RoleEntity
import javax.persistence.*


@Entity(name = "user")
@Table(name = "user")
class UserEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        val id: Long? ,
        var email: String?,
        var password: String?,
        var avatarUrl: String?,
        var firstName: String?,
        var lastName: String?,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "role_id")
        var roleEntity: RoleEntity?
) {
    constructor(): this(id = null)
    constructor(id: Long?): this(id, null, null, null, null, null, null)
}
