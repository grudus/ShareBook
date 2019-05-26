package com.pwr.sharebook.attachments

import org.springframework.web.multipart.MultipartFile
import java.io.OutputStream

interface AttachmentIoService<ID> {
    fun downloadById(id: ID, outputStream: OutputStream)
    fun upload(multiPartFile: MultipartFile, fileName: String): ID
}
