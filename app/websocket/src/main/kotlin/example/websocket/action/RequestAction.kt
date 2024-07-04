package example.websocket.action

enum class RequestAction(
    private val description: String,
) {
    CREATE("create"),
    JOIN("join"),
    CHAT("chat"),
}
