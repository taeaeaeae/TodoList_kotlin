package taekyoung.TodoList.domain.todos.controller

import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import taekyoung.TodoList.domain.todos.dto.CreateTodoRequest
import taekyoung.TodoList.domain.todos.dto.GetTodoResponse
import taekyoung.TodoList.domain.todos.dto.TodoResponse
import taekyoung.TodoList.domain.todos.dto.UpdateTodoRequest
import taekyoung.TodoList.domain.todos.service.TodoService

@RequestMapping("/todos")
@RestController
class TodoController(
    private val service: TodoService
) {
    @PostMapping
    fun createTodo( @Valid @RequestBody createTodoRequest: CreateTodoRequest): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTodo(createTodoRequest))
    }

    @GetMapping()
    fun getTodoList(
//        @SortDefault(sort = ["desc"], direction = Sort.Direction.ASC)
        @RequestParam uid: String?,
        @PageableDefault(size = 10, page = 0) pageable: Pageable
    ): ResponseEntity<Page<TodoResponse>> {
        val principal = SecurityContextHolder.getContext().authentication.principal
        println("~~~~~~~~~~~~~~~~~~~~~~~ ${principal} ~~~~~~~~~~~~~~~~~~~~")
        pageable.sort.descending()
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllTodo(pageable,uid))
    }

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<GetTodoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTodoById(todoId))
    }

    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @RequestBody  @Valid updateCourseRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateTodo(todoId, updateCourseRequest))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        service.deleteTodo(todoId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}