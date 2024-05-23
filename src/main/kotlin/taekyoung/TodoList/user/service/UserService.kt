package taekyoung.TodoList.user.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import taekyoung.TodoList.user.dto.LoginRequest
import taekyoung.TodoList.user.dto.SignUpUserRequest
import taekyoung.TodoList.user.dto.UserResponse
import taekyoung.TodoList.user.model.User
import taekyoung.TodoList.user.model.toResponse
import taekyoung.TodoList.user.repository.UserRepository

@Service
class UserService(
    private val repository: UserRepository,
    private val encoder: PasswordEncoder,
) {
    fun signUp(request: SignUpUserRequest): UserResponse {
        println("????????????${request.pw}????????????")
        println("!!!!!!!!!!!${encoder.encode(request.pw)}?????????????!!!")
        return repository.save(
            User(
                id= request.id,
                pw=encoder.encode(request.pw)
            )
        ).toResponse()
    }
    fun login(request: LoginRequest): UserResponse {
        TODO()
    }

//    fun updateUser???????


}