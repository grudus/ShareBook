package com.pwr.sharebook.notification.event

import com.pwr.sharebook.event.EventPublisher
import com.pwr.sharebook.group.post.AddPostRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class NotificationEventPublisher
@Autowired
constructor(private val notificationEventFactory: NotificationEventFactory,
            private val eventPublisher: EventPublisher)
{
    private val logger = LoggerFactory.getLogger(javaClass)

    fun publishNewPostInGroup(addPostRequest: AddPostRequest, groupId: Long, id: Long) {
        val event = notificationEventFactory.newPostInGroup(addPostRequest, groupId, id)
        logger.info("Publishing event about new post in group: {}", event)
        eventPublisher.publish(event)
    }

}
