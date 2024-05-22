package taekyoung.TodoList.todos.dto

import java.time.LocalDateTime

data class GetTodoResponse (
    val id: Long,
    val title: String,
    val content: String,
    val yn : Boolean,
    val uid: String,
    val reply: List<ReplyResponse>,
    val createdAt: LocalDateTime
)