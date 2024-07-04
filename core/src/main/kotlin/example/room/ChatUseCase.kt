package example.room

interface ChatUseCase {
    fun chat(sessionId: String, roomId: String, message: String)
}
