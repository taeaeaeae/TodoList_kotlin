package taekyoung.TodoList.domain.todos.dto

import taekyoung.TodoList.domain.user.dto.UserResponse
import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val yn : Boolean,
    val uid: UserResponse,
    val createAt: LocalDateTime
//    val createAt:Date,
//    val reply: List<ReplyResponse>
)