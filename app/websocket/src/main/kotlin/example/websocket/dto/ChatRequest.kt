package example.websocket.dto

data class ChatRequest(
    val roomId: String,
    val message: String,
)
