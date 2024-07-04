package example.room

import example.event.BroadcastEvent
import example.event.BroadcastEvent.Status.OK
import example.event.EventPublisher
import org.springframework.stereotype.Service

@Service
internal class ChatService(
    private val eventPublisher: EventPublisher,
    private val roomRepository: RoomRepository,
) : ChatUseCase {
    override fun chat(sessionId: String, roomId: String, message: String) {
        val roomInfo = roomRepository.getRoomInfoWithout(sessionId, roomId)

        eventPublisher.publishBroadcastEvent(BroadcastEvent(roomInfo.sessionIds, OK, "CHAT", message))
    }
}
