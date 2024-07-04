package example.auth

import example.user.UserId

interface AuthRepository {
    fun setUserId(sessionId: String, userId: UserId)

    fun getUserId(sessionId: String): UserId?

    fun deleteUserId(sessionId: String)
}
