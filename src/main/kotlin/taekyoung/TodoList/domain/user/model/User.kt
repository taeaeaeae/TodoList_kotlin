package taekyoung.TodoList.domain.user.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import taekyoung.TodoList.domain.user.dto.UserResponse

@Entity
@Table(name = "users")
class User (
    @Id
    @Column(name = "id")
    val id: String,

    @Column(name = "password")
    val pw: String
) {
}

fun User.toResponse() : UserResponse {
    return UserResponse(
        id = id,
        pw = pw
    )
}