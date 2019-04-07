package com.pwr.sharebook.user.registration

import com.pwr.sharebook.common.IdResponse
import com.pwr.sharebook.user.auth.AuthSecurityUserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class UserRegistrationController
@Autowired
constructor(private val userRegistrationService: UserRegistrationService,
            private val registrationValidator: RegistrationValidator,
            private val authSecurityUserService: AuthSecurityUserService
            )
{
    private val logger = LoggerFactory.getLogger(UserRegistrationController::class.java)


    @PostMapping("/register")
    @ResponseStatus(CREATED)
    fun createUser(@RequestBody @Valid createUserRequest: CreateUserRequest): IdResponse {
        logger.info("Creating user ${createUserRequest.email}")
        return IdResponse(userRegistrationService.createUser(createUserRequest))
    }

    @PostMapping("/logout")
    fun logout(response: HttpServletResponse, request: HttpServletRequest) {
        logger.info("Logout user")
        authSecurityUserService.removeAuthCookie(request, response)
    }


    @InitBinder("createUserRequest")
    protected fun initEditBinder(binder: WebDataBinder) {
        binder.validator = registrationValidator
    }
}
