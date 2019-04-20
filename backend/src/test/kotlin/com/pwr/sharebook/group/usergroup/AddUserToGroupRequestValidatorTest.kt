package com.pwr.sharebook.group.usergroup

import com.pwr.sharebook.common.RestKeys
import com.pwr.sharebook.group.GroupService
import com.pwr.sharebook.user.UserEntity
import com.pwr.sharebook.user.UserService
import com.pwr.sharebook.user.auth.AuthSecurityUserService
import com.pwr.sharebook.utils.RandomUtils.randomEmail
import com.pwr.sharebook.utils.RandomUtils.randomId
import com.pwr.sharebook.utils.RandomUtils.randomText
import com.pwr.sharebook.utils.ValidatorUtils
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class AddUserToGroupRequestValidatorTest {

    @Mock
    lateinit var userService: UserService

    @Mock
    lateinit var userGroupService: UserGroupService

    @Mock
    lateinit var authSecurityUserService: AuthSecurityUserService

    lateinit var validator: AddUserToGroupRequestValidator

    @Before
    fun init() {
        validator = AddUserToGroupRequestValidator(userService, userGroupService, authSecurityUserService)

        `when`(userService.findByEmail(anyString())).thenReturn(UserEntity(randomId()))
        `when`(userGroupService.groupWasCreatedByUser(anyLong(), anyLong())).thenReturn(true)
        `when`(userGroupService.userExistsInGroup(anyLong(), anyLong())).thenReturn(false)
    }

    @Test
    fun shouldValidateProperly() {
        val request = AddUserToGroupRequest(randomEmail(), randomId())

        val errors = validator.validate(request)

        assertTrue(errors.isEmpty())
    }

    @Test
    fun shouldNotPassValidationWhenInvalidEmail() {
        val request = AddUserToGroupRequest(randomText(), randomId())

        val errors = validator.validate(request)

        ValidatorUtils.assertErrorCodes(errors, RestKeys.INVALID_EMAIL)
    }

    @Test
    fun shouldNotPassValidationWhenEmptyEmail() {
        val request = AddUserToGroupRequest("", randomId())

        val errors = validator.validate(request)

        ValidatorUtils.assertErrorCodes(errors, RestKeys.EMPTY_EMAIL)
    }

    @Test
    fun shouldNotPassValidationWhenGroupWasCreatedBySomeoneElse() {
        `when`(userGroupService.groupWasCreatedByUser(anyLong(), anyLong())).thenReturn(false)

        val request = AddUserToGroupRequest(randomEmail(), randomId())

        val errors = validator.validate(request)

        ValidatorUtils.assertErrorCodes(errors, RestKeys.UNAUTHORIZED)
    }

    @Test
    fun shouldNotPassValidationWhenUserNotExists() {
        `when`(userService.findByEmail(anyString())).thenReturn(null)

        val request = AddUserToGroupRequest(randomEmail(), randomId())

        val errors = validator.validate(request)

        ValidatorUtils.assertErrorCodes(errors, RestKeys.USER_DOES_NOT_EXISTS)
    }

    @Test
    fun shouldNotPassValidationWhenUserAlreadyExistsInGroup() {
        `when`(userGroupService.userExistsInGroup(anyLong(), anyLong())).thenReturn(true)

        val request = AddUserToGroupRequest(randomEmail(), randomId())

        val errors = validator.validate(request)

        ValidatorUtils.assertErrorCodes(errors, RestKeys.USER_ALREADY_EXISTS_IN_GROUP)
    }
}
