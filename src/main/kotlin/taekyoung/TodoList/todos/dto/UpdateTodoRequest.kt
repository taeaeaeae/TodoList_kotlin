package taekyoung.TodoList.todos.dto

data class UpdateTodoRequest (
    val title: String,
    val content: String,
    val yn: Boolean
)