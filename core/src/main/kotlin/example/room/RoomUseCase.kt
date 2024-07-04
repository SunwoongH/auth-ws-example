package example.room

interface RoomUseCase {
    fun createRoom(sessionId: String)

    fun joinRoom(sessionId: String, roomId: String)

    fun leaveRoom(sessionId: String, roomId: String)
}
