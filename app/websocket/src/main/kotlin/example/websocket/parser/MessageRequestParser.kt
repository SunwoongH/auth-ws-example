package example.websocket.parser

import example.websocket.action.RequestAction
import example.websocket.dto.MessageRequest
import org.springframework.stereotype.Component

@Component
class MessageRequestParser {
    fun parse(message: String): MessageRequest {
        val i = message.indexOf('\n')
        if (i == -1) {
            throw RuntimeException()
        }
        return MessageRequest(
            RequestAction.valueOf(message.substring(0, i)),
            message.substring(i + 1),
        )
    }
}
