package com.pwr.sharebook.websocket

import org.springframework.context.ApplicationEvent

abstract class WebsocketEvent(source: Any): ApplicationEvent(source) {
}
