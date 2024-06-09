package taekyoung.TodoList.domain.user.service

import com.teamsparta.courseregistation.domain.user.exception.InvalidCredentialException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import taekyoung.TodoList.domain.exception.ModelNotFoundException
import taekyoung.TodoList.domain.user.dto.LoginRequest
import taekyoung.TodoList.domain.user.dto.LoginResponse
import taekyoung.TodoList.domain.user.dto.SignUpUserRequest
import taekyoung.TodoList.domain.user.dto.UserResponse
import taekyoung.TodoList.domain.user.model.User
import taekyoung.TodoList.domain.user.model.UserRole
import taekyoung.TodoList.domain.user.model.toResponse
import taekyoung.TodoList.domain.user.repository.UserRepository
import taekyoung.TodoList.infra.security.jwt.TokenProvider

@Service
class UserService(
    private val repository: UserRepository,
    private val encoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider,
) {
    fun signUp(request: SignUpUserRequest): UserResponse {
        println("????????????${request.pw}????????????")
        println("!!!!!!!!!!!${encoder.encode(request.pw)}?????????????!!!")
        return repository.save(
            User(
                id= request.id,
                pw=encoder.encode(request.pw),
                role = when (request.role) {
                    UserRole.GUEST.name -> UserRole.GUEST
                    UserRole.HOST.name -> UserRole.HOST
                    else -> throw IllegalArgumentException("Invalid role")
                }
            )
        ).toResponse()
    }
    fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByIdOrNull(request.id)?: throw ModelNotFoundException("User",null,)

        if(user.role.name != request.role || !(passwordEncoder.matches(request.pw, user.pw))) {
            throw InvalidCredentialException()
        }
        return LoginResponse(
            accessToken = tokenProvider.generateAccessToken(
                subject = user.id,
                uid = user.id,
                role = user.role.name
            )
        )
    }

//    fun updateUser???????


}