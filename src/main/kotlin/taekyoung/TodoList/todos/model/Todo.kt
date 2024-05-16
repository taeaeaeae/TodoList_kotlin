package taekyoung.TodoList.todos.model

import jakarta.persistence.*
import taekyoung.TodoList.reply.model.Reply
import taekyoung.TodoList.todos.dto.TodoResponse

@Entity
@Table(name = "todo_list")
class Todo (
    @Column(name="title")
    var title: String,

    @Column(name="content")
    var content: String,

    @Column(name="uid")
    var uid: String,

    @Column(name = "y_n")
    var yn: Boolean = false,

    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY, orphanRemoval = true)
    var replys: MutableList<Reply> = mutableListOf(),
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null

    fun removeReply(reply: Reply) {
        replys.remove(reply)
    }
}
fun Todo.toResponse() : TodoResponse {
    return TodoResponse(
        id=id!!,
        title=title,
        content=content,
        uid=uid,
        yn=yn
    )
}