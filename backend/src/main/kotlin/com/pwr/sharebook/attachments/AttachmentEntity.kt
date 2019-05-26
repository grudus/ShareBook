package com.pwr.sharebook.attachments

import com.pwr.sharebook.group.post.PostEntity
import javax.persistence.*


@Entity(name = "post_attachments")
@Table(name = "post_attachments")
class AttachmentEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        val id: Long?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "post_id")
        val postEntity: PostEntity?,

        val location: String?,
        val originalFilename: String?
) {
    constructor(): this(null, null, null, null)
}
