package example.user

@JvmInline
value class UserId(
    val value: Long,
)

data class User(
    val userId: UserId,
    val name: String,
)
