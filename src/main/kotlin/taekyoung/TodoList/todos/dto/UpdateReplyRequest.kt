package taekyoung.TodoList.todos.dto

data class UpdateReplyRequest (
    val content: String,
    val uid: String,
    val pw : String
)