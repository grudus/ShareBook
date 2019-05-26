package com.pwr.sharebook.attachments

import com.pwr.sharebook.common.StringIdResponse
import com.pwr.sharebook.common.io.HttpUtils
import com.pwr.sharebook.user.AuthenticatedUser
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("groups/{groupId}/posts/{postId}")
class PostAttachmentController
@Autowired
constructor(private val attachmentService: AttachmentService) {
    private val logger = LoggerFactory.getLogger(javaClass)


    @PostMapping("/attachments")
    fun uploadFile(@RequestBody file: MultipartFile,
                   @PathVariable("groupId") groupId: Long,
                   @PathVariable("postId") postId: Long,
                   user: AuthenticatedUser): StringIdResponse {
        logger.info("User {} uploads file {}", user.email, file.originalFilename)
        return StringIdResponse(attachmentService.addAttachmentToPost(file, groupId, postId, user))
    }

    @GetMapping("/attachments/{attachmentId}")
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
