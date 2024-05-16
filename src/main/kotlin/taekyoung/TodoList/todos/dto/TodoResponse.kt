package taekyoung.TodoList.todos.dto

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val yn : Boolean = false,
    val uid: String
)