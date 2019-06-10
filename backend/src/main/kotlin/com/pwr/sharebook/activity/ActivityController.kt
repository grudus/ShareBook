package com.pwr.sharebook.activity

import com.pwr.sharebook.user.AuthenticatedUser
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/activity")
class ActivityController
@Autowired
constructor(private val activityService: ActivityService)
{
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun getUserActivity(user: AuthenticatedUser, @RequestParam("year") year: Int): UserActivityResult {
        logger.info("Generate user activity data for user {}", user)
        Thread.sleep(500)
        return activityService.getUserActivity(user.id, year)
    }

}
