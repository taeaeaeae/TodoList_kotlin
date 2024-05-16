package taekyoung.TodoList.todos.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import taekyoung.TodoList.todos.model.Todo

interface TodoRepository : JpaRepository<Todo, Long> {
}