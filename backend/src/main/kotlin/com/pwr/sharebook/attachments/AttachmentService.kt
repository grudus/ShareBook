package com.pwr.sharebook.attachments

import com.pwr.sharebook.common.exceptions.CannotFindIdException
import com.pwr.sharebook.user.AuthenticatedUser
import org.apache.commons.io.FilenameUtils
import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
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


    fun addAttachment(file: MultipartFile): String {
        val fileName = createFilename(file)
        val locationId = attachmentIoService.upload(file, fileName)
        val originalFilename = file.originalFilename

        logger.info("Successfully uploaded file $originalFilename. Get id: $locationId")

        val attachment = AttachmentEntity(null, locationId, originalFilename)
        attachmentRepository.save(attachment)
        return locationId
    }

    fun findAttachmentByLocation(location: String): AttachmentEntity? =
            attachmentRepository.findByLocation(location)

    fun downloadById(id: String, outputStream: OutputStream) {
        attachmentIoService.downloadById(id, outputStream)
    }

    fun findFilenameByLocation(attachmentId: String): String? =
            attachmentRepository.findByLocation(attachmentId)
                    ?.originalFilename

    private fun createFilename(file: MultipartFile) =
            "${randomAlphabetic(5)}_${LocalDateTime.now().nano}.${FilenameUtils.getExtension(file.originalFilename)}"

}
