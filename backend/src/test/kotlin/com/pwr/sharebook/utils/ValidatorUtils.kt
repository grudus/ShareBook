package com.pwr.sharebook.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue


object ValidatorUtils {

    fun assertErrorCodes(errors: List<String>, vararg codes: String) {
        assertEquals(codes.size, errors.size)
        codes.forEach {
            assertTrue("Cannot find error [$it] in $errors", errors.contains(it))
        }
    }
}
