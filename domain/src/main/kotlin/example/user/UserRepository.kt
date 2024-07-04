package example.user

interface UserRepository {
    fun getUser(userId: UserId): User?

    fun createUser(name: String): User
}
