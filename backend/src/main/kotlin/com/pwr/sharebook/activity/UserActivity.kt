package com.pwr.sharebook.activity

import java.time.Month

data class UserActivity(
        val numberOfPosts: Int,
        val numberOfComments: Int,
        val numberOfAttachments: Int,
        val month: Month
)
