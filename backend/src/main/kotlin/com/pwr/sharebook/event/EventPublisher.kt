package com.pwr.sharebook.event

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class EventPublisher
@Autowired
constructor(private val applicationEventPublisher: ApplicationEventPublisher)
{
    private val logger = LoggerFactory.getLogger(javaClass)

    fun <T: ApplicationEvent> publish(event: T) {
        logger.info("Publishing event {}", event)
        applicationEventPublisher.publishEvent(event)
    }
}
