package taekyoung.TodoList.domain.todos.dto

data class AddReplyRequest (
    val content: String,
    val uid: String,
    val pw: String
)