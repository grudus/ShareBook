package com.pwr.sharebook.user.registration

import com.pwr.sharebook.AbstractControllerTest
import com.pwr.sharebook.utils.RandomUtils.randomEmail
import com.pwr.sharebook.utils.RandomUtils.randomText
import org.hamcrest.Matchers.notNullValue
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class UserRegistrationControllerTest : AbstractControllerTest() {

    private val BASE_URL = "/auth/register"

    @Test
    fun shouldCreateUserAndReturnId() {
        val request = randomCreateUserRequest()

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
        )
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.id", notNullValue()))
    }

    @Test
    fun `should be able to login after successful registration`() {
        val request = randomCreateUserRequest()

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isCreated)

        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(buildUrlEncodedFormEntity("email", request.email, "password", request.password)))
                .andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.header().exists("Set-Cookie"))
    }

    @Test
    fun `should return 400 when invalid request`() {
        val request = CreateUserRequest(randomEmail(), randomText(), randomText(), randomText(), randomText())

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isBadRequest)
    }

    private fun randomCreateUserRequest(): CreateUserRequest {
        val password = randomText()
        return CreateUserRequest(randomEmail(), password, password, randomText(), randomText())
    }


}
