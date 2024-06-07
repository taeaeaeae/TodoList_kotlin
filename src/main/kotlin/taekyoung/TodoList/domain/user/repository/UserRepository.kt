package taekyoung.TodoList.domain.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import taekyoung.TodoList.domain.user.model.User

interface UserRepository : JpaRepository<User, String> {
}