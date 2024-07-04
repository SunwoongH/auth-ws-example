package example.dto

import example.auth.token.Token

data class TokenResponse(
    val accessToken: String,
)

fun Token.toResponse() = TokenResponse(accessToken)
