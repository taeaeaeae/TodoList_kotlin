package taekyoung.TodoList.domain.user.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import taekyoung.TodoList.domain.user.dto.LoginRequest
import taekyoung.TodoList.domain.user.dto.LoginResponse
import taekyoung.TodoList.domain.user.dto.SignUpUserRequest
import taekyoung.TodoList.domain.user.dto.UserResponse
import taekyoung.TodoList.domain.user.service.UserService

@RestController
@RequestMapping("/users")
class UserController (
    private val service: UserService
) {
    @PostMapping("/signup")
    fun signUp(
        @Valid @RequestBody signup : SignUpUserRequest
    ) : ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.signUp(signup))
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(service.login(request))
    }


}