package com.pwr.sharebook.attachments

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController("/attachments")
class AttachmentController
@Autowired
constructor(private val attachmentService: AttachmentService){


    @GetMapping
    fun getAll() {
        attachmentService.getAllFiles()
    }
}
