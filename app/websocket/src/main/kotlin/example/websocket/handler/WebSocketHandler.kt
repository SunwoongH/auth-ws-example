package example.websocket.handler

import example.websocket.SessionManager
import example.websocket.parser.MessageRequestParser
import example.websocket.router.WebSocketMessageRouter
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class WebSocketHandler(
    private val sessionManager: SessionManager,
    private val parser: MessageRequestParser,
    private val router: WebSocketMessageRouter,
    private val authenticator: WebSocketAuthenticationHandler,
) : TextWebSocketHandler() {
    override fun afterConnectionEstablished(session: WebSocketSession) {
        println("afterConnectionEstablished() called")
        sessionManager.startSession(session)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val messageRequest = parser.parse(message.payload)
        router.route(messageRequest, session.id)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        println("afterConnectionClosed() called")
        authenticator.deleteAuthenticatedUserId(session.id)
        sessionManager.endSession(session)
    }
}
