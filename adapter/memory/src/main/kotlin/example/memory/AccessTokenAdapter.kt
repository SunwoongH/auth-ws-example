package example.memory

import example.auth.token.AccessTokenRepository
import example.auth.token.Token
import example.support.jwt.JwtProvider
import example.support.jwt.JwtSecretKey
import example.user.UserId
import java.time.LocalDateTime
import org.springframework.stereotype.Component

@Component
internal class AccessTokenAdapter(
    private val jwtProvider: JwtProvider,
    private val secretKey: JwtSecretKey,
) : AccessTokenRepository {
    override fun generate(userId: UserId): Token {
        val now = LocalDateTime.now()
        val accessToken = jwtProvider.generate(
            id = userId.value.toString(),
            expiration = now.plusMinutes(10),
            key = secretKey,
        )

        return Token(accessToken)
    }
}
