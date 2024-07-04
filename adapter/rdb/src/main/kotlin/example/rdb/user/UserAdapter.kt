package example.rdb.user

import example.user.User
import example.user.UserId
import example.user.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
internal class UserAdapter(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {
    override fun getUser(userId: UserId): User? {
        return userJpaRepository.findByIdOrNull(userId.value)?.toDomain()
    }

    override fun createUser(name: String): User {
        val user = UserJpaEntity.from(name)
        val savedUser = userJpaRepository.save(user)

        return savedUser.toDomain()
    }
}
