package taekyoung.TodoList.reply.dto

data class UpdateReplyRequest (
    val content: String,
    val uid: String,
    val pw : String
)