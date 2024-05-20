package taekyoung.TodoList.todos.repository

import org.springframework.data.jpa.repository.JpaRepository
import taekyoung.TodoList.todos.model.Reply

interface ReplyRepository : JpaRepository<Reply, Long> {
    fun findByTodoIdAndId(todoId: Long, replyId: Long): Reply?
}