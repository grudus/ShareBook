package com.pwr.sharebook.comment

import com.pwr.sharebook.group.post.PostEntity
import com.pwr.sharebook.user.UserEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "comment")
@Table(name = "comment")
data class CommentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    val createdAt: LocalDateTime?,
    val text: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    val postEntity: PostEntity?,


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    val createdBy: UserEntity?
) {
    constructor(): this(null, null, null, null, null)
}
