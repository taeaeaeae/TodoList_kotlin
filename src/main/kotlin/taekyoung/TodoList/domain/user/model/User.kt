package taekyoung.TodoList.domain.user.model

import jakarta.persistence.*
import taekyoung.TodoList.domain.user.dto.UserResponse

@Entity
@Table(name = "users")
class User (
    @Id
    @Column(name = "id")
    val id: String,

    @Column(name = "password")
    val pw: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: UserRole,
) {
}

fun User.toResponse() : UserResponse {
    return UserResponse(
        id = id,
//        pw = pw,
        role = role.name
    )
}