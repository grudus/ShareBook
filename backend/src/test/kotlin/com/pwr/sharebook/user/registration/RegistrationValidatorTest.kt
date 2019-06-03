package com.pwr.sharebook.user.registration

import com.pwr.sharebook.common.RestKeys.EMAIL_EXISTS
import com.pwr.sharebook.common.RestKeys.EMPTY_EMAIL
import com.pwr.sharebook.common.RestKeys.EMPTY_NAME
import com.pwr.sharebook.common.RestKeys.EMPTY_PASSWORD
import com.pwr.sharebook.common.RestKeys.INVALID_EMAIL
import com.pwr.sharebook.common.RestKeys.PASSWORD_NOT_MATCHES
import com.pwr.sharebook.common.validator.EmailValidator
import com.pwr.sharebook.utils.RandomUtils.randomEmail
import com.pwr.sharebook.utils.RandomUtils.randomText
import com.pwr.sharebook.utils.ValidatorUtils.assertErrorCodes
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RegistrationValidatorTest {

    lateinit var validator: RegistrationValidator

    @Mock
    lateinit var emailValidator: EmailValidator

    @Before
    fun init() {
        Mockito.`when`(emailValidator.validateEmail(anyString())).thenReturn(emptyList())
        validator = RegistrationValidator(emailValidator)
    }

    @Test
    fun `should pass validation when valid request`() {
        val password = randomText()
        val request = CreateUserRequest(randomEmail(), password, password, randomText(), randomText())

        val errors = validator.validate(request)

        assertTrue(errors.isEmpty())
    }


    @Test
    fun `should fail validation when invalid email`() {
        Mockito.`when`(emailValidator.validateEmail(anyString())).thenCallRealMethod()
        val password = randomText()
        val request = CreateUserRequest(randomText(), password, password, randomText(), randomText())

        val errors = validator.validate(request)

        assertErrorCodes(errors, INVALID_EMAIL)
    }

    @Test
    fun `should fail validation when no email`() {
        Mockito.`when`(emailValidator.validateEmail(anyString())).thenCallRealMethod()
        val password = randomText()
        val request = CreateUserRequest("", password, password, randomText(), randomText())

        val errors = validator.validate(request)

        assertErrorCodes(errors, EMPTY_EMAIL)
    }

    @Test
    fun `should fail validation when password not matches`() {
        val request = CreateUserRequest(randomEmail(), randomText(11), randomText(12), randomText(), randomText())

        val errors = validator.validate(request)

        assertErrorCodes(errors, PASSWORD_NOT_MATCHES)
    }

    @Test
    fun `should fail validation when empty password`() {
        val password = randomText()
        val request = CreateUserRequest(randomEmail(), "", password, randomText(), randomText())

        val errors = validator.validate(request)

        assertErrorCodes(errors, EMPTY_PASSWORD)
    }

    @Test
    fun `should fail validation when no name`() {
        val password = randomText()
        val request = CreateUserRequest(randomEmail(), password, password, "", randomText())

        val errors = validator.validate(request)

        assertErrorCodes(errors, EMPTY_NAME)
    }

    @Test
    fun `should fail validation when email exists`() {
        Mockito.`when`(emailValidator.validateEmail(anyString())).thenReturn(listOf(EMAIL_EXISTS))
        val password = randomText()
        val request = CreateUserRequest(randomEmail(), password, password, randomText(), randomText())

        val errors = validator.validate(request)

        assertErrorCodes(errors, EMAIL_EXISTS)
    }

    @Test
    fun `should fail validation when no data`() {
        Mockito.`when`(emailValidator.validateEmail(anyString())).thenCallRealMethod()
        val request = CreateUserRequest("", "", "", "", "")

        val errors = validator.validate(request)

        assertErrorCodes(errors, EMPTY_PASSWORD, EMPTY_EMAIL, EMPTY_NAME)
    }


}
