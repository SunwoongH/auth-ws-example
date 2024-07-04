package example.exception

sealed class CustomException(val code: String, message: String, cause: Throwable? = null) : RuntimeException(message, cause)

sealed class ClientException(code: String, message: String, cause: Throwable? = null) : CustomException(code, message, cause)

sealed class ServerException(code: String, message: String, cause: Throwable? = null) : CustomException(code, message, cause)

sealed class CriticalException(code: String, message: String, cause: Throwable? = null) : CustomException(code, message, cause)

data object UnAuthorizedException : ClientException("auth401", "인가 실패") {
    private fun readResolve(): Any = UnAuthorizedException
}

data object UnAuthenticatedException : ClientException("auth401", "인증 실패") {
    private fun readResolve(): Any = UnAuthenticatedException
}

data object InternalServerException : ServerException("s001", "서버 내부 오류") {
    private fun readResolve(): Any = InternalServerException
}

data object UnknownException : CriticalException("crt001", "정의되지 않은 예외") {
    private fun readResolve(): Any = UnknownException
}
