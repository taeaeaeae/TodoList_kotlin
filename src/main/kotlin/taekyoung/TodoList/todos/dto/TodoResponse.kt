package taekyoung.TodoList.todos.dto

import taekyoung.TodoList.reply.dto.ReplyResponse
import java.util.Date

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val yn : Boolean = false,
    val uid: String,
//    val createAt:Date,
    val reply: List<ReplyResponse>
)