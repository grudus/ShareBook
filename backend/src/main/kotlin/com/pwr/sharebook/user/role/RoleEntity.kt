package com.pwr.sharebook.user.role

import javax.persistence.*

@Table(name = "role")
@Entity
data class RoleEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "role_id")
        val id: Long? = null,


        @Enumerated(EnumType.STRING)
        @Column(unique = true)
        val name: RoleType? = null
)
