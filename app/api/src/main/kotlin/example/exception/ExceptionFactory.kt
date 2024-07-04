package example.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class ExceptionFactory {
    fun create(e: CustomException): ResponseEntity<ErrorResponse> {
        val httpStatus = getHttpStatus(e)
        return ResponseEntity.status(httpStatus)
            .body(ErrorResponse(e.code, e.message ?: e.localizedMessage))
    }

    private fun getHttpStatus(e: CustomException): HttpStatus {
        return when (e) {
            is UnAuthorizedException -> HttpStatus.FORBIDDEN
            is UnAuthenticatedException -> HttpStatus.UNAUTHORIZED
            is ClientException -> HttpStatus.BAD_REQUEST

            is ServerException, is CriticalException -> HttpStatus.INTERNAL_SERVER_ERROR
        }
    }
}
