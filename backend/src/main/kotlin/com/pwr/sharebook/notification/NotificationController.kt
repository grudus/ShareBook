package com.pwr.sharebook.notification

import com.pwr.sharebook.user.AuthenticatedUser
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notifications")
class NotificationController
@Autowired
constructor(private val notificationService: NotificationService) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @GetMapping
    fun getAllNotifications(authenticatedUser: AuthenticatedUser): List<NotificationDto> {
        return notificationService.findAllForUser(authenticatedUser.id)
    }

    @PutMapping("/visit/{id}")
    fun visitNotification(@PathVariable("id") id: Long) {
        logger.info("Visiting notification $id")
        notificationService.visitNotification(id)
    }

}
