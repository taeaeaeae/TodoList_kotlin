package taekyoung.TodoList.domain.todos.repository

import org.springframework.data.jpa.repository.JpaRepository
import taekyoung.TodoList.domain.todos.model.Reply

interface ReplyRepository : JpaRepository<Reply, Long> {
    fun findByTodoIdAndId(todoId: Long, replyId: Long): Reply?
}