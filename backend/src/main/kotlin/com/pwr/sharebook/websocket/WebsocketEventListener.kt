package com.pwr.sharebook.websocket

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent

@Component
class WebsocketEventListener
@Autowired
constructor(private val simpMessagingTemplate: SimpMessagingTemplate) : ApplicationListener<WebsocketEvent> {
    private val logger = LoggerFactory.getLogger(javaClass)


    override fun onApplicationEvent(event: WebsocketEvent) {
        logger.info("Get websocket event {}", event)

        if (event is UpdateNotificationsWebsocketEvent) {
            updateNotifications(event)
        }
    }

    @EventListener
    fun handleSessionConnected(event: SessionConnectEvent) {
        logger.info("New websocket session is connected: [{}]", event.user?.name ?: "Unknown user")
    }

    @EventListener
    fun handleSessionDisconnect(event: SessionDisconnectEvent) {
        logger.info("Websocket session is disconnected: [{}]", event.user?.name ?: "Unknown user")
    }


    private fun updateNotifications(event: UpdateNotificationsWebsocketEvent) {
        logger.info("Sending websocket messages to " + event.usersNames)

        event.usersNames.forEach {
            simpMessagingTemplate.convertAndSendToUser(it, "/queue/notifications", "Update notification for: $it")
        }
    }
}
