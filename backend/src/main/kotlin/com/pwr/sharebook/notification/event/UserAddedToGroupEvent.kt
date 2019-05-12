package com.pwr.sharebook.notification.event

class UserAddedToGroupEvent(source: Any,
                            val userCreated: Long,
                            val userAddedEmail: String,
                            val groupId: Long): AbstractNotificationEvent(source) {
}
