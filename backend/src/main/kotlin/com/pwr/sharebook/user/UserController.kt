package com.pwr.sharebook.user

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController
@Autowired
constructor(private val userService: UserService,
            private val updateUserRequestValidator: UpdateUserRequestValidator) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun getCurrentUser(): UserDto {
        logger.info("Get current user info")
        return userService.getCurrentUser()
    }

    @PutMapping
    fun updateUser(@RequestBody @Valid updateUserRequest: UserDto): UserDto {
        logger.info("Updating current user with data {}", updateUserRequest)
        return userService.update(updateUserRequest)
    }



    @InitBinder("userDto")
    protected fun initEditBinder(binder: WebDataBinder) {
        binder.validator = updateUserRequestValidator
    }

}
