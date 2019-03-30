package com.pwr.sharebook.group

import com.pwr.sharebook.user.UserEntity
import java.time.LocalDateTime
import javax.persistence.*


@Entity(name = "class_group")
@Table(name = "class_group")
class GroupEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "class_group_id")
        val id: Long?,
        val name: String?,
        val createdAt: LocalDateTime?,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "created_by")
        val userEntity: UserEntity?
) {
    constructor(): this(null, null, null, null)
}
