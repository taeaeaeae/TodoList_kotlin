package taekyoung.TodoList.domain.todos.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class CreateTodoRequest (
    @field:Size(min = 1, max = 200, message = "Name must be between 1 and 200")
    val title: String,
    @field:Size(min = 1, max = 1000, message = "Description must be between 1 and 1000")
    val content: String,
    @field:NotBlank(message = "uid cannot be blank")
    val uid:String,
    val createAt: LocalDateTime = LocalDateTime.now()
)