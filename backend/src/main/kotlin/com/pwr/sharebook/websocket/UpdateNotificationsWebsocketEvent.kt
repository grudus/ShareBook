package com.pwr.sharebook.websocket

class UpdateNotificationsWebsocketEvent(
        source: Any,
        val usersNames: List<String>

) : WebsocketEvent(source) {
}
