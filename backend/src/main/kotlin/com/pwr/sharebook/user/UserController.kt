package com.pwr.sharebook.user

import com.pwr.sharebook.user.auth.AuthSecurityUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController
@Autowired
constructor(private val authSecurityUserService: AuthSecurityUserService)
{

    @GetMapping
    fun getCurrentUser(): UserEntity {
        return authSecurityUserService.getCurrentUser()
    }

}
