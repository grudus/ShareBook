package com.pwr.sharebook.group.post

import com.pwr.sharebook.common.RestKeys
import com.pwr.sharebook.utils.RandomUtils.randomText
import com.pwr.sharebook.utils.ValidatorUtils.assertErrorCodes
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertTrue

@RunWith(MockitoJUnitRunner::class)
class AddPostRequestValidatorTest {

    lateinit var validator: AddPostRequestValidator

    @Before
    fun init() {
        validator = AddPostRequestValidator()
    }

    @Test
    fun shouldValidateProperly() {
        val request = AddPostRequest(randomText())

        val errors = validator.validate(request)

        assertTrue(errors.isEmpty())
    }

    @Test
    fun shouldNotPassValidationWhenBlankText() {
        val request = AddPostRequest("  \t")

        val errors = validator.validate(request)

        assertErrorCodes(errors, RestKeys.EMPTY_TEXT)
    }

}
