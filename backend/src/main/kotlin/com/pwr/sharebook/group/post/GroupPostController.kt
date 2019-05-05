package com.pwr.sharebook.group.post

import com.pwr.sharebook.common.IdResponse
import com.pwr.sharebook.user.AuthenticatedUser
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("groups/{groupId}/posts")
class GroupPostController
@Autowired
constructor(
        private val groupPostService: GroupPostService,
        private val addPostRequestValidator: AddPostRequestValidator) {
    private val logger = LoggerFactory.getLogger(javaClass)


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addPost(@PathVariable("groupId") groupId: Long,
                @RequestBody @Valid addPostRequest: AddPostRequest,
                user: AuthenticatedUser
    ): IdResponse {
        logger.info("User [{}] adds new post", user)
        return IdResponse(groupPostService.addPost(addPostRequest, groupId, user.id))
    }

    @GetMapping
    fun getPostsForGroup(@PathVariable("groupId") groupId: Long): List<GroupPostDto> {
        return groupPostService.getPostsForGroup(groupId)
    }

    @InitBinder("addPostRequest")
    protected fun initEditBinder(binder: WebDataBinder) {
        binder.validator = addPostRequestValidator
    }
}
