package taekyoung.TodoList.domain.todos.dto

data class ReplyResponse (
    val id:Long,
    val content: String,
    val uid: String,
//    val pw: String
)