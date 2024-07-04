package example.websocket.dto

data class JoinRoomRequest(
    val accessToken: String,
    val roomId: String,
)
