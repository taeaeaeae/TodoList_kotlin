package taekyoung.TodoList.todos.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import taekyoung.TodoList.todos.dto.CreateTodoRequest
import taekyoung.TodoList.todos.dto.TodoResponse
import taekyoung.TodoList.todos.dto.UpdateTodoRequest
import taekyoung.TodoList.exception.ModelNotFoundException
import taekyoung.TodoList.todos.model.Todo
import taekyoung.TodoList.todos.model.toResponse
import taekyoung.TodoList.todos.repository.TodoRepository

@Service
class TodoServiceImpl(
    private val repository: TodoRepository
) : TodoService {
    override fun getAllTodo(): List<TodoResponse> {
        return repository.findAll().map { it.toResponse() }
    }

    override fun getTodoById(todoId: Long): TodoResponse {
        val todo = repository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)
        return todo.toResponse()
    }

    @Transactional
    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        return repository.save(
            Todo(
                title = request.title,
                content = request.content,
                uid = request.uid
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse {
        val todo = repository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)
        val (title, content, yn) = request

        todo.title = if(title == "" || title=="string") todo.title else title
        todo.content = if(content == "" || content == "string") todo.content else content
        todo.yn = yn

        return repository.save(todo).toResponse()
    }

    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = repository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)
        repository.delete(todo)
    }
}