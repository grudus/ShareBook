package com.pwr.sharebook

import com.pwr.sharebook.spring.security.JwtUtils
import com.pwr.sharebook.user.UserEntity
import com.pwr.sharebook.user.auth.UserDetailsImpl
import com.pwr.sharebook.user.auth.UserDetailsServiceImpl
import com.pwr.sharebook.user.registration.UserRegistrationService
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import javax.servlet.http.Cookie


abstract class AuthenticatedControllerTest : AbstractControllerTest() {

    @Autowired
    private lateinit var userDetailsService: UserDetailsServiceImpl

    @Autowired
    private lateinit var jwtUtils: JwtUtils

    private lateinit var authCookie: Cookie

    protected lateinit var currentUser: UserEntity

    @Before
    fun initLogin() {
        login()
        flush()
    }

    fun authGet(urlTemplate: String) =
            mockMvc.perform(get(urlTemplate)
                    .cookie(authCookie))

    fun authPost(urlTemplate: String, body: Any) =
            mockMvc.perform(post(urlTemplate)
                    .cookie(authCookie)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(body)))

    fun authPut(urlTemplate: String, body: Any) =
            mockMvc.perform(put(urlTemplate)
                    .cookie(authCookie)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(body)))


    private fun login() {
        val user = addUser()

        val userDetails: UserDetailsImpl = userDetailsService.loadUserByUsername(user.email!!) as UserDetailsImpl
        val authentication = UsernamePasswordAuthenticationToken(userDetails, userDetails.password)
        authCookie = jwtUtils.authCookie(authentication)
        currentUser = userDetails.user
    }

    protected fun logInAnotherUser() {
        login()
    }

}
