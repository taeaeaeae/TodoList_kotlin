package taekyoung.TodoList.domain.todos.controller

import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import taekyoung.TodoList.domain.todos.dto.CreateTodoRequest
import taekyoung.TodoList.domain.todos.dto.GetTodoResponse
import taekyoung.TodoList.domain.todos.dto.TodoResponse
import taekyoung.TodoList.domain.todos.dto.UpdateTodoRequest
import taekyoung.TodoList.domain.todos.service.TodoService
import taekyoung.TodoList.domain.user.service.UserService
import taekyoung.TodoList.infra.security.UserPrincipal

@RequestMapping("/todos")
@RestController
class TodoController(
    private val service: TodoService,
    private val userService: UserService
) {
    @PostMapping
    @PreAuthorize("hasRole('HOST')")
    fun createTodo( @Valid @RequestBody createTodoRequest: CreateTodoRequest): ResponseEntity<TodoResponse> {
        val id = userService.getUserDetails()?.uid
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTodo(createTodoRequest,id))
    }

    @GetMapping()
    fun getTodoList(
//        @SortDefault(sort = ["desc"], direction = Sort.Direction.ASC)
        @RequestParam uid: String?,
        @PageableDefault(size = 10, page = 0) pageable: Pageable
    ): ResponseEntity<Page<TodoResponse>> {
        val principal = SecurityContextHolder.getContext().authentication.principal
        pageable.sort.descending()
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllTodo(pageable,uid))
    }

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<GetTodoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTodoById(todoId))
    }

    @PutMapping("/{todoId}")
    @PreAuthorize("hasRole('HOST')")
    fun updateTodo(
        @PathVariable todoId: Long,
        @RequestBody  @Valid updateCourseRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        val id = userService.getUserDetails()?.uid
        return ResponseEntity.status(HttpStatus.OK).body(service.updateTodo(todoId, updateCourseRequest, id))
    }

    @DeleteMapping("/{todoId}")
    @PreAuthorize("hasRole('HOST')")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        val id = userService.getUserDetails()?.uid
        service.deleteTodo(todoId, id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}