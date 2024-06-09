package taekyoung.TodoList.domain.todos.service

import com.teamsparta.courseregistation.domain.user.exception.InvalidCredentialException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import taekyoung.TodoList.domain.exception.ModelNotFoundException
import taekyoung.TodoList.domain.todos.dto.AddReplyRequest
import taekyoung.TodoList.domain.todos.dto.ReplyResponse
import taekyoung.TodoList.domain.todos.dto.UpdateReplyRequest
import taekyoung.TodoList.domain.todos.model.Reply
import taekyoung.TodoList.domain.todos.model.toResponse
import taekyoung.TodoList.domain.todos.repository.ReplyRepository
import taekyoung.TodoList.domain.todos.repository.TodoRepository
import taekyoung.TodoList.domain.user.repository.UserRepository

@Service
class ReplyServiceImpl(
    private val repository: ReplyRepository,
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository
) : ReplyService {

    override fun getReplyList(todoId: Long): List<ReplyResponse> {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)
//        return todo.replys.map { it.toResponse() }
//        val reply : Page<Todo> = repository.findAll(pageable)
        return repository.findAll().map { it.toResponse() }
    }

    @Transactional
    override fun addReply(todoId: Long, request: AddReplyRequest, id: String): ReplyResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)
        val user = userRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("User", 0)

        return repository.save(
            Reply(
                content = request.content,
                uid = user,
                todo = todo
            )
        ).toResponse()
    }

    @Transactional
    override fun updateReply(todoId: Long, replyId: Long, request: UpdateReplyRequest, id: String): ReplyResponse {
        val reply = repository.findByTodoIdAndId(todoId, replyId) ?: throw ModelNotFoundException("Reply",replyId)
        val user = userRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("User", 0)

        if(user.id != reply.uid.id) {
            throw InvalidCredentialException()
        }

        reply.content = request.content

        return repository.save(reply).toResponse()
    }

    @Transactional
    override fun deleteReply(todoId: Long, replyId: Long, id: String) {
        val reply = repository.findByIdOrNull(replyId) ?: throw ModelNotFoundException("Reply",replyId)
        val user = userRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("User", 0)
        if(user.id != reply.uid.id) {
            throw InvalidCredentialException()
        }
        repository.delete(reply)
    }

}