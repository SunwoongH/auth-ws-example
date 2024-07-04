package example.websocket.dto

import example.websocket.action.RequestAction

data class MessageRequest(
    val action: RequestAction,
    val body: String? = null,
)
