package taekyoung.TodoList.todos.service

import taekyoung.TodoList.reply.dto.AddReplyRequest
import taekyoung.TodoList.reply.dto.ReplyResponse
import taekyoung.TodoList.reply.dto.UpdateReplyRequest
import taekyoung.TodoList.todos.dto.CreateTodoRequest
import taekyoung.TodoList.todos.dto.TodoResponse
import taekyoung.TodoList.todos.dto.UpdateTodoRequest

interface TodoService {
    fun getAllTodo() : List<TodoResponse>
    fun getTodoById(todoId:Long) : TodoResponse
    fun createTodo(request : CreateTodoRequest): TodoResponse
    fun updateTodo(todoId: Long, request : UpdateTodoRequest) : TodoResponse
    fun deleteTodo(todoId: Long)
    fun getReplyList(todoId: Long): List<ReplyResponse>
    fun addReply(todoId: Long, request : AddReplyRequest) : ReplyResponse
    fun updateReply(todoId: Long, replyId: Long, request: UpdateReplyRequest): ReplyResponse
    fun deleteReply(todoId: Long, replyId: Long)
}