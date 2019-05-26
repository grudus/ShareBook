package com.pwr.sharebook.attachments

import com.pwr.sharebook.group.post.PostEntity
import com.pwr.sharebook.user.AuthenticatedUser
import org.apache.commons.io.FilenameUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.OutputStream
import java.time.LocalDateTime

@Service
class AttachmentService
@Autowired
constructor(
        private val attachmentIoService: AttachmentIoService<String>,
        private val attachmentRepository: AttachmentRepository
) {
    private val logger = LoggerFactory.getLogger(javaClass)


    fun addAttachmentToPost(file: MultipartFile, groupId: Long, postId: Long, user: AuthenticatedUser): String {
        val fileName = "${user.email}_${groupId}_${postId}_${LocalDateTime.now().nano}.${FilenameUtils.getExtension(file.originalFilename)}"
        val id = attachmentIoService.upload(file, fileName)
        val originalFilename = file.originalFilename

        logger.info("Successfully uploaded file $originalFilename. Get id: $id")

        val attachment = AttachmentEntity(null, PostEntity(postId), id, originalFilename)
        attachmentRepository.save(attachment)
        return id
    }

    fun downloadById(id: String, outputStream: OutputStream) {
        attachmentIoService.downloadById(id, outputStream)
    }

    fun findFilenameByLocation(attachmentId: String): String? =
            attachmentRepository.findByLocation(attachmentId)
                    ?.originalFilename

}
