package taekyoung.TodoList.domain.todos.service

import taekyoung.TodoList.domain.todos.dto.AddReplyRequest
import taekyoung.TodoList.domain.todos.dto.ReplyResponse
import taekyoung.TodoList.domain.todos.dto.UpdateReplyRequest

interface ReplyService {
    fun getReplyList(todoId: Long): List<ReplyResponse>
    fun addReply(todoId: Long, request : AddReplyRequest) : ReplyResponse
    fun updateReply(todoId: Long, replyId: Long, request: UpdateReplyRequest): ReplyResponse
    fun deleteReply(todoId: Long, replyId: Long)
}