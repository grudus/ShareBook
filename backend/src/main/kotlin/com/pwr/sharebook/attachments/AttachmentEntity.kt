package com.pwr.sharebook.attachments

import javax.persistence.*


@Entity(name = "attachment")
@Table(name = "attachment")
class AttachmentEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "attachment_id")
        val id: Long?,

        val location: String?,
        val originalFilename: String?
) {
    constructor() : this(null, null, null)
}
