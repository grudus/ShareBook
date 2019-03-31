package com.pwr.sharebook.group

import com.pwr.sharebook.AuthenticatedControllerTest
import com.pwr.sharebook.utils.RandomUtils.randomText
import org.hamcrest.Matchers.*
import org.junit.Test
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class GroupControllerTest : AuthenticatedControllerTest() {
    val baseUrl = "/groups"


    @Test
    fun shouldReturnEmptyListWhenNoGroups() {
        authGet(baseUrl)
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.[*]", empty<String>()))
    }

    @Test
    fun shouldCreateGroup() {
        val request = CreateGroupRequest(randomText())
        authPost(baseUrl, request)
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.id", notNullValue()))
    }

    @Test
    fun shouldReturnAllUserGroups() {
        (0 until 5)
                .forEach { _ -> authPost(baseUrl, CreateGroupRequest(randomText())) }

        flush()

        authGet(baseUrl)
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.[*]", hasSize<Int>(5)))
    }


    @Test
    fun shouldOnlyGetGroupsFromCurrentUser() {
        (0 until 5)
                .forEach { _ -> authPost(baseUrl, CreateGroupRequest(randomText())) }

        logInAnotherUser()

        (0 until 10)
                .forEach { _ -> authPost(baseUrl, CreateGroupRequest(randomText())) }

        flush()

        authGet(baseUrl)
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.[*]", hasSize<Int>(10)))
    }

    @Test
    fun shouldNotBeAbleToCreateInvalidGroup() {
        val request = CreateGroupRequest("")
        authPost(baseUrl, request)
                .andExpect(status().isBadRequest)
    }


}
