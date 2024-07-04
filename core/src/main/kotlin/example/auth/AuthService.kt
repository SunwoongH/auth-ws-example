package example.auth

import example.auth.token.AccessTokenRepository
import example.auth.token.Token
import example.user.UserId
import example.user.UserRepository
import org.springframework.stereotype.Component

@Component
internal class AuthService(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val accessTokenRepository: AccessTokenRepository,
) : AuthUseCase {
    override fun authenticate(sessionId: String, userId: UserId?) {
        if (userId != null) {
            authRepository.setUserId(sessionId, userId)
            return
        }
        authRepository.getUserId(sessionId) ?: throw RuntimeException("Unauthorized user")
    }

    override fun deleteAuthenticatedUserId(sessionId: String) {
        authRepository.deleteUserId(sessionId)
    }

    override fun anonymous(): Token {
        val user = userRepository.createUser("anonymous")

        return accessTokenRepository.generate(user.userId)
    }
}
