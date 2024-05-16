package taekyoung.TodoList.todos.model

import jakarta.persistence.*
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
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null

//    fun seccess()
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