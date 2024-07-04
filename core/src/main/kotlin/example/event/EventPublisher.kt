package example.event

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
internal class EventPublisher(
    private val publisher: ApplicationEventPublisher,
) {
    fun publishBroadcastEvent(event: BroadcastEvent) {
        publisher.publishEvent(event)
    }
}
