package taekyoung.TodoList.domain.todos.dto

import taekyoung.TodoList.domain.user.dto.UserResponse

data class AddReplyRequest (
    val content: String,
    val uid: UserResponse,
    val pw: String
)