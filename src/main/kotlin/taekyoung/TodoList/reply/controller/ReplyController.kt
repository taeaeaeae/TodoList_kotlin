package taekyoung.TodoList.reply.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import taekyoung.TodoList.reply.dto.AddReplyRequest
import taekyoung.TodoList.reply.dto.ReplyResponse
import taekyoung.TodoList.reply.dto.UpdateReplyRequest
import taekyoung.TodoList.reply.model.Reply
import taekyoung.TodoList.todos.service.TodoService

@RequestMapping("/todos/{todoId}/reply")
@RestController
class ReplyController (
    private val todoService: TodoService
) {
    @GetMapping
    fun getReplyList(@PathVariable("todoId") todoId: Long): ResponseEntity<List<ReplyResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getReplyList(todoId))
    }

    @PostMapping
    fun addReply(@PathVariable("todoId") todoId: Long, @RequestBody addReplyRequest: AddReplyRequest): ResponseEntity<ReplyResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.addReply(todoId, addReplyRequest))
    }

    @PutMapping("/{replyId}")
    fun updateReply(@PathVariable("todoId") todoId: Long, @PathVariable("replyId") replyId: Long,
                    @RequestBody updateReplyRequest: UpdateReplyRequest): ResponseEntity<ReplyResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.updateReply(todoId, replyId, updateReplyRequest))
    }

    @DeleteMapping("/{replyId}")
    fun removeReply(@PathVariable("todoId") todoId: Long,@PathVariable("replyId") replyId: Long): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }


}