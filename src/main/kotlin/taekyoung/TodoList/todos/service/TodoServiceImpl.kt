package taekyoung.TodoList.todos.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import taekyoung.TodoList.todos.dto.CreateTodoRequest
import taekyoung.TodoList.todos.dto.TodoResponse
import taekyoung.TodoList.todos.dto.UpdateTodoRequest
import taekyoung.TodoList.exception.ModelNotFoundException
import taekyoung.TodoList.reply.dto.AddReplyRequest
import taekyoung.TodoList.reply.dto.ReplyResponse
import taekyoung.TodoList.reply.dto.UpdateReplyRequest
import taekyoung.TodoList.reply.model.Reply
import taekyoung.TodoList.reply.model.toResponse
import taekyoung.TodoList.reply.repository.ReplyRepository
import taekyoung.TodoList.todos.model.Todo
import taekyoung.TodoList.todos.model.toResponse
import taekyoung.TodoList.todos.repository.TodoRepository

@Service
class TodoServiceImpl(
    private val repository: TodoRepository,
    private val todoRepository: TodoRepository,
    private val replyRepository: ReplyRepository
) : TodoService {
    override fun getAllTodo(pageable: Pageable): List<TodoResponse> {
        val todo : Page<Todo> = repository.findAll(pageable)
        return todo.content.map { it.toResponse() }
    }

    override fun getTodoById(todoId: Long): TodoResponse {
        val todo = repository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)
        return todo.toResponse()
    }

    @Transactional
    override fun createTodo(request: CreateTodoRequest): TodoResponse {
        return repository.save(
            Todo(
                title = request.title,
                content = request.content,
                uid = request.uid
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodo(todoId: Long, request: UpdateTodoRequest): TodoResponse {
        val todo = repository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)
        val (title, content, yn) = request

        todo.title = if(title == "" || title=="string") todo.title else title
        todo.content = if(content == "" || content == "string") todo.content else content
        todo.yn = yn

        return repository.save(todo).toResponse()
    }

    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = repository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)
        repository.delete(todo)
    }

    override fun getReplyList(todoId: Long): List<ReplyResponse> {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)
        return todo.replys.map { it.toResponse() }
    }

    @Transactional
    override fun addReply(todoId: Long, request: AddReplyRequest): ReplyResponse {
        val todo = repository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)

        return replyRepository.save(
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
        val reply = replyRepository.findByTodoIdAndId(todoId, replyId) ?: throw ModelNotFoundException("Reply",replyId)

        val (content,uid,pw) = request
        if(reply.uid == uid && reply.pw == pw) {
            reply.content = content
        }
        return replyRepository.save(reply).toResponse()
    }

    @Transactional
    override fun deleteReply(todoId: Long, replyId: Long) {
        val todo = repository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo",todoId)
        val reply = replyRepository.findByIdOrNull(replyId) ?: throw ModelNotFoundException("Reply",replyId)

        todo.removeReply(reply)
        repository.save(todo)
    }

}