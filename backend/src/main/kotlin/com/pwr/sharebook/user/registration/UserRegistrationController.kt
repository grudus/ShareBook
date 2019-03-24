package com.pwr.sharebook.user.registration

import com.pwr.sharebook.common.IdResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class UserRegistrationController
@Autowired
constructor(private val userRegistrationService: UserRegistrationService,
            private val registrationValidator: RegistrationValidator)
{
    private val logger = LoggerFactory.getLogger(UserRegistrationController::class.java)




    @PostMapping("/register")
    fun createUser(@RequestBody @Valid createUserRequest: CreateUserRequest): IdResponse {
        logger.info("Creating user ${createUserRequest.email}")
        return IdResponse(userRegistrationService.createUser(createUserRequest))
    }


    @InitBinder("createUserRequest")
    protected fun initEditBinder(binder: WebDataBinder) {
        binder.validator = registrationValidator
    }
}
