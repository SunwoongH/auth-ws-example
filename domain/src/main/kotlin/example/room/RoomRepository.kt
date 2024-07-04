package example.room

interface RoomRepository {
    fun joinRoom(sessionId: String, roomId: String)

    fun leaveRoom(sessionId: String, roomId: String)

    fun getRoomInfoWithout(sessionId: String, roomId: String): RoomInfo
}
