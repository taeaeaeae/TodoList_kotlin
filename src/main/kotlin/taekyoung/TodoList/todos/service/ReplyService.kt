package taekyoung.TodoList.todos.service

import taekyoung.TodoList.todos.dto.AddReplyRequest
import taekyoung.TodoList.todos.dto.ReplyResponse
import taekyoung.TodoList.todos.dto.UpdateReplyRequest

interface ReplyService {
    fun getReplyList(todoId: Long): List<ReplyResponse>
    fun addReply(todoId: Long, request : AddReplyRequest) : ReplyResponse
    fun updateReply(todoId: Long, replyId: Long, request: UpdateReplyRequest): ReplyResponse
    fun deleteReply(todoId: Long, replyId: Long)
}