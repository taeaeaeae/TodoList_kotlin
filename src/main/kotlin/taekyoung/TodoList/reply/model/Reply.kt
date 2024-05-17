package taekyoung.TodoList.reply.model

import jakarta.persistence.*
import taekyoung.TodoList.reply.dto.ReplyResponse
import taekyoung.TodoList.todos.model.Todo

@Entity
@Table(name = "reply")
class Reply(
    @Column(name = "content")
    var content: String,

    @Column(name = "uid")
    var uid: String,

    @Column(name = "pw")
    var pw: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    val todo: Todo,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
fun Reply.toResponse(): ReplyResponse {
    return ReplyResponse(
        id = id!!,
        content = content,
        uid = uid
//        pw = pw
    )
}