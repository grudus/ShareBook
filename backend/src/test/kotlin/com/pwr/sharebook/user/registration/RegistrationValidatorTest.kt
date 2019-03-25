package com.pwr.sharebook.user.registration

import com.pwr.sharebook.common.RestKeys.EMAIL_EXISTS
import com.pwr.sharebook.common.RestKeys.EMPTY_EMAIL
import com.pwr.sharebook.common.RestKeys.EMPTY_NAME
import com.pwr.sharebook.common.RestKeys.EMPTY_PASSWORD
import com.pwr.sharebook.common.RestKeys.INVALID_EMAIL
import com.pwr.sharebook.common.RestKeys.PASSWORD_NOT_MATCHES
import com.pwr.sharebook.utils.RandomUtils.randomEmail
import com.pwr.sharebook.utils.RandomUtils.randomText
import com.pwr.sharebook.utils.ValidatorUtils.assertErrorCodes
import com.pwr.sharebook.utils.ValidatorUtils.getErrors
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegistrationValidatorTest {

    @InjectMocks
    lateinit var validator: RegistrationValidator

    @Mock
    lateinit var userRegistrationService: UserRegistrationService

    @Before
    fun init() {
        Mockito.`when`(userRegistrationService.emailExists(anyString())).thenReturn(false)
    }

    @Test
    fun `should pass validation when valid request`() {
        val password = randomText()
        val request = CreateUserRequest(randomEmail(), password, password, randomText(), randomText())

        val errors = getErrors(request)
        validator.validate(request, errors)

        assertFalse(errors.hasErrors())
    }


    @Test
    fun `should fail validation when invalid email`() {
        val password = randomText()
        val request = CreateUserRequest(randomText(), password, password, randomText(), randomText())

        val errors = getErrors(request)
        validator.validate(request, errors)

        assertErrorCodes(errors, INVALID_EMAIL)
    }

    @Test
    fun `should fail validation when no email`() {
        val password = randomText()
        val request = CreateUserRequest("", password, password, randomText(), randomText())

        val errors = getErrors(request)
        validator.validate(request, errors)

        assertErrorCodes(errors, EMPTY_EMAIL)
    }

    @Test
    fun `should fail validation when password not matches`() {
        val request = CreateUserRequest(randomEmail(), randomText(11), randomText(12), randomText(), randomText())

        val errors = getErrors(request)
        validator.validate(request, errors)

        assertErrorCodes(errors, PASSWORD_NOT_MATCHES)
    }

    @Test
    fun `should fail validation when empty password`() {
        val password = randomText()
        val request = CreateUserRequest(randomEmail(), "", password, randomText(), randomText())

        val errors = getErrors(request)
        validator.validate(request, errors)

        assertErrorCodes(errors, EMPTY_PASSWORD)
    }

    @Test
    fun `should fail validation when no name`() {
        val password = randomText()
        val request = CreateUserRequest(randomEmail(), password, password, "", randomText())

        val errors = getErrors(request)
        validator.validate(request, errors)

        assertErrorCodes(errors, EMPTY_NAME)
    }

    @Test
    fun `should fail validation when email exists`() {
        Mockito.`when`(userRegistrationService.emailExists(anyString())).thenReturn(true)
        val password = randomText()
        val request = CreateUserRequest(randomEmail(), password, password, randomText(), randomText())

        val errors = getErrors(request)
        validator.validate(request, errors)

        assertErrorCodes(errors, EMAIL_EXISTS)
    }

    @Test
    fun `should fail validation when no data`() {
        val request = CreateUserRequest("", "", "", "", "")

        val errors = getErrors(request)
        validator.validate(request, errors)

        assertErrorCodes(errors, EMPTY_PASSWORD, EMPTY_EMAIL, EMPTY_NAME)
    }


}
