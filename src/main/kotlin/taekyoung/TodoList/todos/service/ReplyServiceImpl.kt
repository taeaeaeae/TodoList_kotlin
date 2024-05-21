package taekyoung.TodoList.todos.service

import org.springframework.data.domain.Page
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import taekyoung.TodoList.exception.ModelNotFoundException
import taekyoung.TodoList.todos.dto.AddReplyRequest
import taekyoung.TodoList.todos.dto.ReplyResponse
import taekyoung.TodoList.todos.dto.UpdateReplyRequest
import taekyoung.TodoList.todos.model.Reply
import taekyoung.TodoList.todos.model.Todo
import taekyoung.TodoList.todos.model.toResponse
import taekyoung.TodoList.todos.repository.ReplyRepository
import taekyoung.TodoList.todos.repository.TodoRepository

@Service
class ReplyServiceImpl(
    private val repository: ReplyRepository,
    private val todoRepository: TodoRepository
) : ReplyService {

    override fun getReplyList(todoId: Long): List<ReplyResponse> {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)
//        return todo.replys.map { it.toResponse() }
//        val reply : Page<Todo> = repository.findAll(pageable)
        return repository.findAll().map { it.toResponse() }
    }

    @Transactional
    override fun addReply(todoId: Long, request: AddReplyRequest): ReplyResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)

        return repository.save(
            Reply(
                content = request.content,
                uid = request.uid,
                pw = request.pw,
                todo = todo
            )
        ).toResponse()
    }

    @Transactional
    override fun updateReply(todoId: Long, replyId: Long, request: UpdateReplyRequest): ReplyResponse {
        val reply = repository.findByTodoIdAndId(todoId, replyId) ?: throw ModelNotFoundException("Reply",replyId)

        val (content,uid,pw) = request
        if(reply.uid == uid && reply.pw == pw) {
            reply.content = content
        }
        return repository.save(reply).toResponse()
    }

    @Transactional
    override fun deleteReply(todoId: Long, replyId: Long) {
        val reply = repository.findByIdOrNull(replyId) ?: throw ModelNotFoundException("Reply",replyId)

        repository.delete(reply)
    }

}