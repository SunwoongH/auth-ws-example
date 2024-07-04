package example.auth

import example.support.jwt.JwtProvider
import example.support.jwt.JwtSecretKey
import example.user.UserId
import org.springframework.stereotype.Service

@Service
internal class JwtService(
    private val jwtProvider: JwtProvider,
    private val jwtSecretKey: JwtSecretKey,
) : TokenUseCase {
    override fun getSubject(token: String): UserId? {
        val subject = jwtProvider.validateAndGetSubject(token, jwtSecretKey) ?: return null

        return UserId(subject.toLong())
    }
}
