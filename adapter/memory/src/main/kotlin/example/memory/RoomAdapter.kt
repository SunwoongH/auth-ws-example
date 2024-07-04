package example.memory

import example.room.RoomInfo
import example.room.RoomRepository
import java.util.concurrent.ConcurrentHashMap
import org.springframework.stereotype.Component

@Component
internal class RoomAdapter : RoomRepository {
    private val rooms = ConcurrentHashMap<String, MutableSet<String>>()

    override fun joinRoom(sessionId: String, roomId: String) {
        var sessionIds = rooms[roomId]
        if (sessionIds == null) {
            sessionIds = mutableSetOf()
            rooms[roomId] = sessionIds
        }
        sessionIds.add(sessionId)
    }

    override fun leaveRoom(sessionId: String, roomId: String) {
        val sessionIds = rooms[roomId] ?: throw RuntimeException()
        sessionIds.remove(sessionId)

        if (sessionIds.isEmpty()) {
            rooms.remove(roomId)
        }
    }

    override fun getRoomInfoWithout(sessionId: String, roomId: String): RoomInfo {
        val sessionIds = mutableSetOf<String>()

        rooms[roomId]?.let {
            it.asSequence()
                .filter { id ->
                    id != sessionId
                }
                .forEach { id ->
                    sessionIds.add(id)
                }
        } ?: throw RuntimeException()

        return RoomInfo(sessionIds)
    }
}
