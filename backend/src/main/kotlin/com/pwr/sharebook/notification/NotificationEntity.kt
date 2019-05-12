package com.pwr.sharebook.notification

import com.pwr.sharebook.user.UserEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "notification")
@Table(name = "notification")
class NotificationEntity (
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "id")
            val id: Long?,
            val title: String?,
            val text: String?,
            @Column(name = "link_href")
            val linkHref: String?,
            val createdAt: LocalDateTime?,
            val visited: Boolean?,
            @ManyToOne(fetch = FetchType.LAZY)
            @JoinColumn(name = "user_id")
            val userEntity: UserEntity?
    ) {
    constructor(): this(null, null, null, null, null, null, null)
}
