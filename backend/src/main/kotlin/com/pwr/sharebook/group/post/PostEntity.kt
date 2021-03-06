package com.pwr.sharebook.group.post

import com.pwr.sharebook.attachments.AttachmentEntity
import com.pwr.sharebook.comment.CommentEntity
import com.pwr.sharebook.group.GroupEntity
import com.pwr.sharebook.user.UserEntity
import java.time.LocalDateTime
import javax.persistence.*


@Entity(name = "post")
@Table(name = "post")
class PostEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "post_id")
        val id: Long?,

        val createdAt: LocalDateTime?,
        val text: String?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "class_group_id")
        val groupEntity: GroupEntity?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "created_by")
        val userEntity: UserEntity?,

        @OneToMany(mappedBy = "postEntity", fetch = FetchType.LAZY)
        val comments: List<CommentEntity>? = emptyList(),

        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinTable(
                name = "post_attachment",
                joinColumns = [JoinColumn(name = "post_id")],
                inverseJoinColumns = [JoinColumn(name = "attachment_id")]
        )
        val attachments: List<AttachmentEntity>? = emptyList()

) {
    constructor() : this(null)
    constructor(id: Long?) : this(id, null, null, null, null, null, null)
}
