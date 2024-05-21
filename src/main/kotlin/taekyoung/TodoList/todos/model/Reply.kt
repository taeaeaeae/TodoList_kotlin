package taekyoung.TodoList.todos.model

import jakarta.persistence.*
import taekyoung.TodoList.todos.dto.ReplyResponse

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
//    @Column(name = "id")
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