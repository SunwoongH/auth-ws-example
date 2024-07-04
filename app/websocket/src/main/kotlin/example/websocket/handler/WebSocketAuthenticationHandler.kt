package example.websocket.handler

import example.auth.AuthUseCase
import example.auth.TokenUseCase
import org.springframework.stereotype.Component

@Component
class WebSocketAuthenticationHandler(
    private val tokenUseCase: TokenUseCase,
    private val authUseCase: AuthUseCase,
) {
    fun authenticate(sessionId: String) {
        authUseCase.authenticate(sessionId)
    }

    fun authenticate(sessionId: String, token: String) {
        val userId = tokenUseCase.getSubject(token) ?: throw RuntimeException()

        authUseCase.authenticate(sessionId, userId)
    }

    fun deleteAuthenticatedUserId(sessionId: String) {
        authUseCase.deleteAuthenticatedUserId(sessionId)
    }
}
