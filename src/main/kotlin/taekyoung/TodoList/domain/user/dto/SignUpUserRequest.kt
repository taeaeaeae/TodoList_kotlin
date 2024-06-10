package taekyoung.TodoList.domain.user.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class SignUpUserRequest (
    @field:NotBlank(message = "id cannot be empty")
    val id: String,
    @field:Pattern(regexp = "^([a-zA-Z]){5,15}$", message = "5~15")
    val pw: String,
    val role: String
)