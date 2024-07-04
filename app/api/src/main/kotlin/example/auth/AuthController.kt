package example.auth

import example.dto.TokenResponse
import example.dto.toResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authUseCase: AuthUseCase,
) {
    @PostMapping("/api/auth/anonymous")
    fun anonymous(): TokenResponse {
        val token = authUseCase.anonymous()

        return token.toResponse()
    }
}
