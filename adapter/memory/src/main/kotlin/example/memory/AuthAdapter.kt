package example.memory

import example.auth.AuthRepository
import example.user.UserId
import java.util.concurrent.ConcurrentHashMap
import org.springframework.stereotype.Component

@Component
internal class AuthAdapter : AuthRepository {
    private val session = ConcurrentHashMap<String, UserId>()

    override fun setUserId(sessionId: String, userId: UserId) {
        session[sessionId] = userId
        println(session.keys)
        println(session.values)
    }

    override fun getUserId(sessionId: String): UserId? {
        println(session.keys)
        println(session.values)
        return session[sessionId]
    }

    override fun deleteUserId(sessionId: String) {
        session.remove(sessionId)
    }
}
