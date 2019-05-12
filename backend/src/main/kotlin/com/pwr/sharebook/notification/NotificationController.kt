package com.pwr.sharebook.notification

import com.pwr.sharebook.user.AuthenticatedUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notifications")
class NotificationController
@Autowired
constructor(private val notificationService: NotificationService)
{

    @GetMapping
    fun getAllNotifications(authenticatedUser: AuthenticatedUser): List<NotificationDto> {
        return notificationService.findAllForUser(authenticatedUser.id)
    }

}
