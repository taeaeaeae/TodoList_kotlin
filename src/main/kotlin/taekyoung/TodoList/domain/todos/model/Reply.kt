package taekyoung.TodoList.domain.todos.model

import jakarta.persistence.*
import taekyoung.TodoList.domain.todos.dto.ReplyResponse
import taekyoung.TodoList.domain.user.model.User
import taekyoung.TodoList.domain.user.model.toResponse

@Entity
@Table(name = "reply")
class Reply(
    @Column(name = "content")
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    var uid: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    val todo: Todo,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    var id: Long? = null
}
fun Reply.toResponse(): ReplyResponse {
    return ReplyResponse(
        id = id!!,
        content = content,
        uid = uid.toResponse()
//        pw = pw
    )
}