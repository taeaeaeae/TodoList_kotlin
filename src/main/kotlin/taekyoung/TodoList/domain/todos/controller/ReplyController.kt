package taekyoung.TodoList.domain.todos.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import taekyoung.TodoList.domain.todos.dto.AddReplyRequest
import taekyoung.TodoList.domain.todos.dto.ReplyResponse
import taekyoung.TodoList.domain.todos.dto.UpdateReplyRequest
import taekyoung.TodoList.domain.todos.service.ReplyService
import taekyoung.TodoList.domain.user.service.UserService

@RequestMapping("/todos/{todoId}/reply")
@RestController
class ReplyController(
    private val service: ReplyService,
    private val userService: UserService,
) {
    @GetMapping
    fun getReplyList(@PathVariable("todoId") todoId: Long): ResponseEntity<List<ReplyResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(service.getReplyList(todoId))
    }

    @PostMapping
    @PreAuthorize("hasRole('HOST') or hasRole('GEUST')")
    fun addReply(@PathVariable("todoId") todoId: Long, @RequestBody addReplyRequest: AddReplyRequest): ResponseEntity<ReplyResponse> {
        val id = userService.getUserDetails()!!.uid
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addReply(todoId, addReplyRequest,id))
    }

    @PutMapping("/{replyId}")
    @PreAuthorize("hasRole('HOST') or hasRole('GEUST')")
    fun updateReply(@PathVariable("todoId") todoId: Long, @PathVariable("replyId") replyId: Long,
                    @RequestBody updateReplyRequest: UpdateReplyRequest
    ): ResponseEntity<ReplyResponse> {
        val id = userService.getUserDetails()!!.uid
        return ResponseEntity.status(HttpStatus.OK).body(service.updateReply(todoId, replyId, updateReplyRequest,id))
    }

    @DeleteMapping("/{replyId}")
    @PreAuthorize("hasRole('HOST') or hasRole('GEUST')")
    fun removeReply(@PathVariable("todoId") todoId: Long,@PathVariable("replyId") replyId: Long): ResponseEntity<Unit> {
        val id = userService.getUserDetails()!!.uid
        service.deleteReply(todoId, replyId,id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}