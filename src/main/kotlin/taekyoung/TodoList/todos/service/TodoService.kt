package taekyoung.TodoList.todos.service

import org.springframework.data.domain.Pageable
import taekyoung.TodoList.todos.dto.*

interface TodoService {
    fun getAllTodo(pageable: Pageable, uid:String) : List<TodoResponse>
    fun getTodoById(todoId:Long) : GetTodoResponse
    fun createTodo(request : CreateTodoRequest): TodoResponse
    fun updateTodo(todoId: Long, request : UpdateTodoRequest) : TodoResponse
    fun deleteTodo(todoId: Long)
}