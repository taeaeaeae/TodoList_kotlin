package taekyoung.TodoList.user.dto

import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @field:NotBlank(message = "???????????")
    val id:String,
    @field:NotBlank(message = "!!!!!!!!!!!!")
    val pw:String
)