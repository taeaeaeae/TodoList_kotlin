package taekyoung.TodoList.todos.service

import taekyoung.TodoList.todos.dto.CreateTodoRequest
import taekyoung.TodoList.todos.dto.TodoResponse
import taekyoung.TodoList.todos.dto.UpdateTodoRequest

interface TodoService {
    fun getAllTodo() : List<TodoResponse>
    fun getTodoById(todoId:Long) : TodoResponse
    fun createTodo(request : CreateTodoRequest): TodoResponse
    fun updateTodo(todoId: Long, request : UpdateTodoRequest) : TodoResponse
    fun deleteTodo(todoId: Long)
}