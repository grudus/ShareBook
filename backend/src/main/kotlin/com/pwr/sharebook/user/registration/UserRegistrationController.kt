package com.pwr.sharebook.user.registration

import com.pwr.sharebook.common.IdResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class UserRegistrationController
@Autowired
constructor(private val userRegistrationService: UserRegistrationService)
{
    private val logger = LoggerFactory.getLogger(UserRegistrationController::class.java)


    @PostMapping("/register")
    fun createUser(@RequestBody request: CreateUserRequest): IdResponse {
        logger.info("Creating user ${request.email}")
        return IdResponse(userRegistrationService.createUser(request))
    }
}
