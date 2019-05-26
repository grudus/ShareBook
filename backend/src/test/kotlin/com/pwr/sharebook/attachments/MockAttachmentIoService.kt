package com.pwr.sharebook.attachments

import com.pwr.sharebook.utils.RandomUtils.randomText
import org.springframework.util.StreamUtils
import org.springframework.web.multipart.MultipartFile
import java.io.OutputStream

class MockAttachmentIoService : AttachmentIoService<String> {
    private val drive = mutableMapOf<String, FileEntity>()

    override fun downloadById(id: String, outputStream: OutputStream) {
        val resource = drive[id]!!.multiPartFile.resource
        StreamUtils.copy(resource.inputStream, outputStream)
    }

    override fun upload(multiPartFile: MultipartFile, fileName: String): String {
        val id = randomText()
        drive[id] = FileEntity(multiPartFile, fileName)
        return id
    }

    fun containsFile(id: String): Boolean = drive.containsKey(id)

    private data class FileEntity(val multiPartFile: MultipartFile, val fileName: String)
}
