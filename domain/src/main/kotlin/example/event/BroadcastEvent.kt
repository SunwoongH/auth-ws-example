package example.event

data class BroadcastEvent(
    val sessionIds: Set<String>,
    val status: Status,
    val action: String,
    val body: Any,
) {
    enum class Status {
        OK,
        ERROR,
    }
}
