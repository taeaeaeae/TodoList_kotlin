package taekyoung.TodoList.user.controller

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import taekyoung.TodoList.user.dto.SignUpUserRequest
import taekyoung.TodoList.user.dto.UserResponse
import taekyoung.TodoList.user.service.UserService

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

    @PostMapping("/login", consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun login(@RequestBody request: SignUpUserRequest){
    }


}