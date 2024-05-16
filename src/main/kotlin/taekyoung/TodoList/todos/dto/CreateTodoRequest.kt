package taekyoung.TodoList.todos.dto

data class CreateTodoRequest (
    val title: String,
    val content: String,
    val uid:String,
)