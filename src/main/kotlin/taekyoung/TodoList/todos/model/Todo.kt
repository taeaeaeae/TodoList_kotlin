package taekyoung.TodoList.todos.model

import jakarta.persistence.*
import taekyoung.TodoList.todos.dto.TodoResponse
import taekyoung.TodoList.todos.dto.UpdateTodoRequest

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

    fun updateTodos(todo: UpdateTodoRequest) {
        title = if(todo.title == "" || todo.title=="string") title else todo.title
        content = if(todo.content == "" || todo.content == "string") content else todo.content
        yn = todo.yn
    }
}
fun Todo.toResponse() : TodoResponse {
    return TodoResponse(
        id=id!!,
        title=title,
        content=content,
        uid=uid,
        yn=yn,
        reply = replys.map { it.toResponse() }
    )
}