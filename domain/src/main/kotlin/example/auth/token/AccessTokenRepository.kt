package example.auth.token

import example.user.UserId

interface AccessTokenRepository {
    fun generate(userId: UserId): Token
}
