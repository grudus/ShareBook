package com.pwr.sharebook.notification.event

import org.springframework.context.ApplicationEvent

abstract class AbstractNotificationEvent(source: Any): ApplicationEvent(source) {
}
