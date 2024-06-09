package taekyoung.TodoList.domain.todos.dto

import org.apache.catalina.User
import taekyoung.TodoList.domain.user.dto.UserResponse
import java.time.LocalDateTime

data class GetTodoResponse (
    val id: Long,
    val title: String,
    val content: String,
    val yn : Boolean,
    val uid: UserResponse,
    val reply: List<ReplyResponse>,
    val createdAt: LocalDateTime
)