package taekyoung.TodoList.todos.controller

import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import taekyoung.TodoList.todos.dto.CreateTodoRequest
import taekyoung.TodoList.todos.dto.GetTodoResponse
import taekyoung.TodoList.todos.dto.TodoResponse
import taekyoung.TodoList.todos.dto.UpdateTodoRequest
import taekyoung.TodoList.todos.service.ReplyService
import taekyoung.TodoList.todos.service.TodoService

@RequestMapping("/todos")
@RestController
class TodoController(
    private val service: TodoService
) {
    @PostMapping
    fun createTodo(@RequestBody createCourseRequest: CreateTodoRequest): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTodo(createCourseRequest))
    }

    @GetMapping()
    fun getTodoList(
        @PageableDefault(size = 10) pageable: Pageable
    ): ResponseEntity<List<TodoResponse>> {
        pageable.sort.descending()
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllTodo(pageable))
    }

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<GetTodoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTodoById(todoId))
    }

    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long,
        @RequestBody updateCourseRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateTodo(todoId, updateCourseRequest))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        service.deleteTodo(todoId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}