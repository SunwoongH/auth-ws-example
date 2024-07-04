package example.rdb.user

import example.user.User
import example.user.UserId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "users")
@Entity
internal class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    var id: Long = 0
        protected set

    @Column(name = "name", nullable = false)
    lateinit var name: String
        protected set

    companion object {
        internal fun from(name: String): UserJpaEntity =
            UserJpaEntity().apply {
                this.name = name
            }
    }
}

internal fun UserJpaEntity.toDomain(): User = User(
    userId = UserId(this.id),
    name = this.name,
)
