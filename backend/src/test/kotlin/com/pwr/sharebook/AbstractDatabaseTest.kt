package com.pwr.sharebook

import com.pwr.sharebook.user.UserEntity
import com.pwr.sharebook.user.UserService
import com.pwr.sharebook.user.registration.CreateUserRequest
import com.pwr.sharebook.user.registration.UserRegistrationService
import com.pwr.sharebook.utils.RandomUtils
import com.pwr.sharebook.utils.RandomUtils.randomEmail
import com.pwr.sharebook.utils.RandomUtils.randomText
import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

abstract class AbstractDatabaseTest : SpringBasedTest() {

    @Autowired
    private lateinit var userRegistrationService: UserRegistrationService

    @Autowired
    private lateinit var userService: UserService

    @PersistenceContext
    private lateinit var entityManager: EntityManager


    fun addUser(email: String = randomEmail()): UserEntity {
        val user = CreateUserRequest(email, "test", "test", randomText(), randomText())
        userRegistrationService.createUser(user)

        flush()

        return userService.findByEmail(email)!!
    }


    /**
     * Clears hibernate cache. Important when saving and writing values from different transactions in one test
     * */
    protected fun flush() {
        entityManager.flush()
        entityManager.clear()
    }
}
