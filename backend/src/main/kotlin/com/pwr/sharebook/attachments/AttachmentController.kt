package com.pwr.sharebook.attachments

import com.pwr.sharebook.common.io.HttpUtils
import com.pwr.sharebook.user.AuthenticatedUser
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("attachments")
class AttachmentController
@Autowired
constructor(private val attachmentService: AttachmentService) {
    private val logger = LoggerFactory.getLogger(javaClass)


    @PostMapping
    fun uploadFile(filepond: MultipartFile): String {
        logger.info("Uploading file {}", filepond.originalFilename)
        return attachmentService.addAttachment(filepond)
    }

    @GetMapping("/{attachmentId}")
    fun downloadById(@PathVariable("attachmentId") attachmentId: String,
                     user: AuthenticatedUser,
                     response: HttpServletResponse) {
        logger.info("User {} is downloading attachment with id {}", user.email, attachmentId)

        val fileName = attachmentService.findFilenameByLocation(attachmentId)
                ?: throw CannotFindAttachmentException(attachmentId)

        HttpUtils.addFilenameToResponse(fileName, response)
        attachmentService.downloadById(attachmentId, response.outputStream)
    }
}
