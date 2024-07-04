package example.room

import example.event.BroadcastEvent
import example.event.BroadcastEvent.Status.OK
import example.event.EventPublisher
import java.util.*
import org.springframework.stereotype.Service

@Service
internal class RoomService(
    private val eventPublisher: EventPublisher,
    private val roomRepository: RoomRepository,
) : RoomUseCase {
    override fun createRoom(sessionId: String) {
        val roomId = generateRoomId()

        roomRepository.joinRoom(sessionId, roomId)

        val sessionIds = mutableSetOf<String>()
        sessionIds.add(sessionId)

        eventPublisher.publishBroadcastEvent(BroadcastEvent(sessionIds, OK, "CREATE", roomId))
    }

    override fun joinRoom(sessionId: String, roomId: String) {
        roomRepository.joinRoom(sessionId, roomId)

        val sessionIds = mutableSetOf<String>()
        sessionIds.add(sessionId)

        eventPublisher.publishBroadcastEvent(BroadcastEvent(sessionIds, OK, "JOIN", roomId))
    }

    override fun leaveRoom(sessionId: String, roomId: String) {
        roomRepository.leaveRoom(sessionId, roomId)
    }

    private fun generateRoomId(): String {
        return UUID.randomUUID().toString()
    }
}
