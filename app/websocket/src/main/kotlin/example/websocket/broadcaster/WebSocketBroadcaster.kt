package example.websocket.broadcaster

import example.event.BroadcastEvent
import example.websocket.SessionManager
import example.websocket.parser.MessageResponseParser
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage

@Component
class WebSocketBroadcaster(
    private val sessionManager: SessionManager,
    private val parser: MessageResponseParser,
) {
    @EventListener
    fun broadcast(event: BroadcastEvent) {
        event.sessionIds
            .asSequence()
            .forEach { sessionId ->
                val message = parser.parse(event)
                val session = sessionManager.getSession(sessionId) ?: throw RuntimeException("No such session")
                session.sendMessage(TextMessage(message))
            }
    }
}
