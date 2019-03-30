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
        val email: String?,
        val password: String?,
        val avatarUrl: String?,
        val firstName: String?,
        val lastName: String?,
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "role_id")
        val roleEntity: RoleEntity?
) {
    constructor(): this(id = null)
    constructor(id: Long?): this(id, null, null, null, null, null, null)
}
