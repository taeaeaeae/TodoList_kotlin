package taekyoung.TodoList.domain.todos.dto

import taekyoung.TodoList.domain.user.dto.UserResponse

data class UpdateReplyRequest (
    val content: String,
    val uid: UserResponse,
)