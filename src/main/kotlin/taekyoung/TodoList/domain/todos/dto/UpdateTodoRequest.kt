package taekyoung.TodoList.domain.todos.dto

data class UpdateTodoRequest (
    var title: String,
    var content: String,
    var yn: Boolean
)