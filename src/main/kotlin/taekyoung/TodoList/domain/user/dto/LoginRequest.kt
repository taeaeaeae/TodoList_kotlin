package taekyoung.TodoList.domain.user.dto

import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank(message = "???????????")
    val id:String,
    @field:NotBlank(message = "!!!!!!!!!!!!")
    val pw:String,
    @field:NotBlank(message = "role!!!!!!")
    val role:String
)