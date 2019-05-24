package com.pwr.sharebook.attachments

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AttachmentService
@Autowired
constructor(private val attachmentRepository: AttachmentRepository){


    fun getAllFiles() {
        attachmentRepository.getAll()
    }
}
