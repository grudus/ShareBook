package com.pwr.sharebook.utils

import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic

object RandomUtils {

    fun randomEmail(): String = randomText() + "@" + "gmail.com"
    fun randomText(length: Int = 11): String = randomAlphabetic(length)

}
