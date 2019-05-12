package com.pwr.sharebook.notification.event

class NewPostInGroupEvent(source: Any,
                          val text: String,
                          val groupId: Long,
                          val userCreatorId: Long) : AbstractNotificationEvent(source) {
}
