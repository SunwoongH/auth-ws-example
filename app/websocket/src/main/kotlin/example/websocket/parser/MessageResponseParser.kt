package example.websocket.parser

import com.fasterxml.jackson.databind.ObjectMapper
import example.event.BroadcastEvent
import example.websocket.action.ResponseAction
import java.lang.StringBuilder
import org.springframework.stereotype.Component

@Component
class MessageResponseParser(
    private val objectMapper: ObjectMapper,
) {
    fun parse(event: BroadcastEvent): String {
        return StringBuilder()
            .append(event.status)
            .append("\n")
            .append(ResponseAction.valueOf(event.action))
            .append("\n")
            .append(objectMapper.writeValueAsString(event))
            .toString()
    }
}
