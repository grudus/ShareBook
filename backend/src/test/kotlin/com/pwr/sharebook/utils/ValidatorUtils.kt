package com.pwr.sharebook.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue


object ValidatorUtils {

    fun assertErrorCodes(errors: List<String>, vararg codes: String) {
        assertEquals(codes.size, errors.size)
        assertTrue(errors.containsAll(listOf(*codes)))
    }
}
