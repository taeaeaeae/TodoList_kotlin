package taekyoung.TodoList.todos.dto

data class GetTodoResponse (
    val id: Long,
    val title: String,
    val content: String,
    val yn : Boolean,
    val uid: String,
    val reply: List<ReplyResponse>
)