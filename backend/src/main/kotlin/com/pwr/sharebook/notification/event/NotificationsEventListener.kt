package com.pwr.sharebook.notification.event

import com.pwr.sharebook.notification.NotificationService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class NotificationsEventListener
@Autowired
constructor(private val notificationService: NotificationService) : ApplicationListener<AbstractNotificationEvent> {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun onApplicationEvent(event: AbstractNotificationEvent) {
        logger.info("Receive application event {}", event)

        if (event is NewPostInGroupEvent) {
            notificationService.save(event)
        }
        else if (event is UserAddedToGroupEvent) {
            notificationService.save(event)
        }
    }
}
