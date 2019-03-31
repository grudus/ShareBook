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
        val request = randomCreateGroupRequest()
        authPost(baseUrl, request)
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.id", notNullValue()))
    }

    @Test
    fun shouldBeAbleToCreateGroupWithoutPhoto() {
        val request = CreateGroupRequest(randomText(), photoUrl = null)
        authPost(baseUrl, request)
                .andExpect(status().isCreated)
                .andExpect(jsonPath("$.id", notNullValue()))
    }

    @Test
    fun shouldReturnAllUserGroups() {
        (0 until 5)
                .forEach { _ -> authPost(baseUrl, randomCreateGroupRequest()) }

        flush()

        authGet(baseUrl)
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.[*]", hasSize<Int>(5)))
    }


    @Test
    fun shouldOnlyGetGroupsFromCurrentUser() {
        (0 until 5)
                .forEach { _ -> authPost(baseUrl, randomCreateGroupRequest()) }

        logInAnotherUser()

        (0 until 10)
                .forEach { _ -> authPost(baseUrl, randomCreateGroupRequest()) }

        flush()

        authGet(baseUrl)
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.[*]", hasSize<Int>(10)))
    }

    private fun randomCreateGroupRequest() = CreateGroupRequest(randomText(), randomText())

    @Test
    fun shouldNotBeAbleToCreateInvalidGroup() {
        val request = CreateGroupRequest("")
        authPost(baseUrl, request)
                .andExpect(status().isBadRequest)
    }


}
