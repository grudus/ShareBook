package com.pwr.sharebook


import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.After
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


abstract class AbstractControllerTest : SpringBasedTest() {


    @Autowired
    private lateinit var wac: WebApplicationContext

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    protected lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        mockMvc = webAppContextSetup(wac)
                .apply<DefaultMockMvcBuilder>(springSecurity())
                .build()
    }

    @After
    fun cleanUp() {
        SecurityContextHolder.clearContext()
    }

    private fun toJson(o: Any): ByteArray =
            objectMapper.writeValueAsBytes(o)

    protected fun buildUrlEncodedFormEntity(vararg params: String): String {
        if (params.size % 2 > 0) {
            throw IllegalArgumentException("Need to give an even number of parameters")
        }
        val result = StringBuilder()
        var i = 0
        while (i < params.size) {
            if (i > 0) {
                result.append('&')
            }
            try {
                result.append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).append('=').append(URLEncoder.encode(params[i + 1], StandardCharsets.UTF_8.name()))
            } catch (e: UnsupportedEncodingException) {
                throw RuntimeException(e)
            }

            i += 2
        }
        return result.toString()
    }
}
