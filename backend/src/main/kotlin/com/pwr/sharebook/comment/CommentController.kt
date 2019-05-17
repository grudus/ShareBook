package com.pwr.sharebook.comment

import com.pwr.sharebook.common.IdResponse
import com.pwr.sharebook.user.AuthenticatedUser
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("groups/{groupId}/posts/{postId}/comments")
class CommentController
@Autowired
constructor(
        private val commentService: CommentService,
        private val addCommentRequestValidator: AddCommentRequestValidator
        ) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addNewComment(
            @PathVariable("postId") postId: Long,
            @RequestBody @Valid addCommentRequest: AddCommentRequest,
            user: AuthenticatedUser
    ): IdResponse {
        logger.info("User {} is adding comment to the post {}", user.email, postId)
        val id = commentService.addComment(addCommentRequest, postId, user.id)
        return IdResponse(id)
    }

    @GetMapping
    fun getCommentsForPost(
            @PathVariable("postId") postId: Long): List<CommentDto> {
        logger.info("Getting comments for post {}", postId)
        return commentService.findCommentsForPost(postId)
    }

    @InitBinder("addCommentRequest")
    protected fun initEditBinder(binder: WebDataBinder) {
        binder.validator = addCommentRequestValidator
    }

}
