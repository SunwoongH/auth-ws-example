package example.auth

import example.user.UserId

interface TokenUseCase {
    fun getSubject(token: String): UserId?
}
