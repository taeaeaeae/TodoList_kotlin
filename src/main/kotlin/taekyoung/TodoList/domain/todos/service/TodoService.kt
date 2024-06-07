package taekyoung.TodoList.domain.todos.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import taekyoung.TodoList.domain.todos.dto.*

interface TodoService {
    fun getAllTodo(pageable: Pageable, uid:String?) : Page<TodoResponse>
    fun getTodoById(todoId:Long) : GetTodoResponse
    fun createTodo(request : CreateTodoRequest): TodoResponse
    fun updateTodo(todoId: Long, request : UpdateTodoRequest) : TodoResponse
    fun deleteTodo(todoId: Long)
}