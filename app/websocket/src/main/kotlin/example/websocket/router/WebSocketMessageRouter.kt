package example.websocket.router

import com.fasterxml.jackson.databind.ObjectMapper
import example.room.ChatUseCase
import example.room.RoomUseCase
import example.websocket.action.RequestAction
import example.websocket.dto.ChatRequest
import example.websocket.dto.CreateRoomRequest
import example.websocket.dto.JoinRoomRequest
import example.websocket.dto.MessageRequest
import example.websocket.handler.WebSocketAuthenticationHandler
import org.springframework.stereotype.Component

@Component
class WebSocketMessageRouter(
    private val authenticator: WebSocketAuthenticationHandler,
    private val objectMapper: ObjectMapper,
    private val roomUseCase: RoomUseCase,
    private val chatUseCase: ChatUseCase,
) {
    fun route(messageRequest: MessageRequest, sessionId: String) {
        when (messageRequest.action) {
            RequestAction.CREATE -> {
                val message = messageRequest.toGenerate<CreateRoomRequest>()

                authenticator.authenticate(sessionId, message.accessToken)

                roomUseCase.createRoom(sessionId)
            }

            RequestAction.JOIN -> {
                val message = messageRequest.toGenerate<JoinRoomRequest>()

                authenticator.authenticate(sessionId, message.accessToken)

                roomUseCase.joinRoom(sessionId, message.roomId)
            }

            RequestAction.CHAT -> {
                val message = messageRequest.toGenerate<ChatRequest>()

                authenticator.authenticate(sessionId)

                chatUseCase.chat(sessionId, message.roomId, message.message)
            }
        }
    }

    private inline fun <reified T : Any> MessageRequest.toGenerate(): T {
        return objectMapper.readValue(this.body, T::class.java)
    }
}
