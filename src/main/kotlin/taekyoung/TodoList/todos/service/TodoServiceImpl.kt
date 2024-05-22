package taekyoung.TodoList.todos.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import taekyoung.TodoList.exception.ModelNotFoundException
import taekyoung.TodoList.todos.dto.*
import taekyoung.TodoList.todos.model.toResponse
import taekyoung.TodoList.todos.model.Todo
import taekyoung.TodoList.todos.model.toGetResponse
import taekyoung.TodoList.todos.repository.TodoRepository
import java.time.LocalDateTime

@Service
class TodoServiceImpl(
    private val repository: TodoRepository
) : TodoService {
    override fun getAllTodo(pageable: Pageable, uid:String?): List<TodoResponse> {

        if(uid.equals("") || uid==null) {
            val todo : Page<Todo> = repository.findAll(pageable)

            return todo.content.map { it.toResponse() }
        }
        val todo : Page<Todo> = repository.findAllByUid(uid, pageable)
        return todo.content.map { it.toResponse() }
    }

    override fun getTodoById(todoId: Long): GetTodoResponse {
        val todo = repository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)
        return todo.toGetResponse()
    }

    @Transactional
    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        return repository.save(
            Todo(
                title = request.title,
                content = request.content,
                uid = request.uid,
                createdAt = request.createAt
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse {
        val todo = repository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)

        todo.updateTodos(request)

        return repository.save(todo).toResponse()
    }

    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = repository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)
        repository.delete(todo)
    }

}