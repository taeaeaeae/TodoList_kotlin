package taekyoung.TodoList.domain.todos.dto

import taekyoung.TodoList.domain.user.dto.UserResponse

data class ReplyResponse (
    val id:Long,
    val content: String,
    val uid: UserResponse,
//    val pw: String
)