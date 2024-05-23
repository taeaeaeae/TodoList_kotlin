package taekyoung.TodoList.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import taekyoung.TodoList.user.model.User

interface UserRepository : JpaRepository<User, String> {
}