package com.pwr.sharebook.attachments

import org.springframework.data.jpa.repository.JpaRepository

interface AttachmentRepository: JpaRepository<AttachmentEntity, Long> {
    fun findByLocation(location: String): AttachmentEntity?
}
