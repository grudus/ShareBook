package com.pwr.sharebook

import com.pwr.sharebook.spring.security.JwtUtils
import com.pwr.sharebook.user.auth.UserDetailsServiceImpl
import com.pwr.sharebook.user.registration.CreateUserRequest
import com.pwr.sharebook.user.registration.UserRegistrationService
import com.pwr.sharebook.utils.RandomUtils.randomEmail
import com.pwr.sharebook.utils.RandomUtils.randomText
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.servlet.http.Cookie


abstract class AuthenticatedControllerTest : AbstractControllerTest() {

    @Autowired
    private lateinit var userDetailsService: UserDetailsServiceImpl

    @Autowired
    private lateinit var jwtUtils: JwtUtils

    private lateinit var authCookie: Cookie

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Autowired
    private lateinit var userRegistrationService: UserRegistrationService

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


    private fun login() {
        val user = CreateUserRequest(randomEmail(), "test", "test", randomText(), randomText())
        userRegistrationService.createUser(user)

        val userDetails: UserDetails = userDetailsService.loadUserByUsername(user.email)
        val authentication = UsernamePasswordAuthenticationToken(userDetails, userDetails.password)
        authCookie = jwtUtils.authCookie(authentication)
    }

    protected fun logInAnotherUser() {
        login()
    }

    /**
     * Clears hibernate cache. Important when saving and writing values from different transactions in one test
     * */
    protected fun flush() {
        entityManager.flush()
        entityManager.clear()
    }

}
