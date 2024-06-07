package taekyoung.TodoList.domain.todos.dto

import java.time.LocalDateTime

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val yn : Boolean,
    val uid: String,
    val createAt: LocalDateTime
//    val createAt:Date,
//    val reply: List<ReplyResponse>
)