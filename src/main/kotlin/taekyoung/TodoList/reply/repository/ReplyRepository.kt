package taekyoung.TodoList.reply.repository

import org.springframework.data.jpa.repository.JpaRepository
import taekyoung.TodoList.reply.model.Reply

interface ReplyRepository : JpaRepository<Reply, Long> {
    fun findByTodoIdAndId(todoId: Long, replyId: Long): Reply?
}