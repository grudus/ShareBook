package com.pwr.sharebook.auth

import com.pwr.sharebook.AbstractControllerTest
import org.junit.Ignore
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Ignore
class LoginTest: AbstractControllerTest() {


    @Test
    fun shouldSaveCookieWhenLoggedWithValidCredentials() {
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(buildUrlEncodedFormEntity("email", "admin", "password", "admin"))
        ).andExpect(status().isOk)
                .andExpect(header().exists("Set-Cookie"))
    }

    @Test
    fun shouldReturn401WhenTryingToLoginWithInvalidCredentials() {
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(buildUrlEncodedFormEntity("email", "notvalidemail", "password", "psswd"))
        ).andExpect(status().isUnauthorized)
                .andExpect(header().doesNotExist("Set-Cookie"))
    }
}
