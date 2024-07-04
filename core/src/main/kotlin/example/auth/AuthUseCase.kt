package example.auth

import example.auth.token.Token
import example.user.UserId

interface AuthUseCase {
    fun authenticate(sessionId: String, userId: UserId? = null)

    fun deleteAuthenticatedUserId(sessionId: String)

    fun anonymous(): Token
}
