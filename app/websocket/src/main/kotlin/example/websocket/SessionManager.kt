package example.websocket

import java.util.concurrent.ConcurrentHashMap
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketSession

@Component
class SessionManager {
    private val sessionStore = ConcurrentHashMap<String, WebSocketSession>()

    fun startSession(session: WebSocketSession) {
        sessionStore.putIfAbsent(session.id, session)
        println(sessionStore[session.id])
    }

    fun endSession(session: WebSocketSession) {
        sessionStore.remove(session.id)
    }

    fun getSession(sessionId: String): WebSocketSession? {
        println(sessionStore[sessionId])
        return sessionStore[sessionId]
    }
}
