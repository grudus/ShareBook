package com.pwr.sharebook.attachments

import java.io.File

interface AttachmentRepository {
    fun upload(file: File): String
    fun download(path: String)
    fun getAll()
}
