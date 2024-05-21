package taekyoung.TodoList.todos.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import taekyoung.TodoList.todos.model.Todo

interface TodoRepository : JpaRepository<Todo, Long> {
    override fun findAll( pageable: Pageable): Page<Todo>
    fun findAllByUid(uid: String, pageable: Pageable): Page<Todo>
}