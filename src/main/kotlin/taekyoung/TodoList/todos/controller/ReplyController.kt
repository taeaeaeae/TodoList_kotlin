package taekyoung.TodoList.todos.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import taekyoung.TodoList.todos.dto.AddReplyRequest
import taekyoung.TodoList.todos.dto.ReplyResponse
import taekyoung.TodoList.todos.dto.UpdateReplyRequest
import taekyoung.TodoList.todos.service.ReplyService

@RequestMapping("/todos/{todoId}/reply")
@RestController
class ReplyController (
    private val service: ReplyService
) {
    @GetMapping
    fun getReplyList(@PathVariable("todoId") todoId: Long): ResponseEntity<List<ReplyResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(service.getReplyList(todoId))
    }

    @PostMapping
    fun addReply(@PathVariable("todoId") todoId: Long, @RequestBody addReplyRequest: AddReplyRequest): ResponseEntity<ReplyResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addReply(todoId, addReplyRequest))
    }

    @PutMapping("/{replyId}")
    fun updateReply(@PathVariable("todoId") todoId: Long, @PathVariable("replyId") replyId: Long,
                    @RequestBody updateReplyRequest: UpdateReplyRequest
    ): ResponseEntity<ReplyResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateReply(todoId, replyId, updateReplyRequest))
    }

    @DeleteMapping("/{replyId}")
    fun removeReply(@PathVariable("todoId") todoId: Long,@PathVariable("replyId") replyId: Long): ResponseEntity<Unit> {
        service.deleteReply(todoId, replyId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}