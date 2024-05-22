package taekyoung.TodoList.todos.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import taekyoung.TodoList.todos.dto.GetTodoResponse
import taekyoung.TodoList.todos.dto.TodoResponse
import taekyoung.TodoList.todos.dto.UpdateTodoRequest
import java.time.LocalDateTime

@Entity
@Table(name = "todo_list")
class Todo (
//    @Size(min = 1, max = 200, message = "Name must be between 1 and 200")
    @Column(name="title")
    var title: String,

//    @Size(min = 1, max = 1000, message = "Description must be between 1 and 1000")
    @Column(name="content")
    var content: String,

//    @NotBlank(message = "uid cannot be blank")
    @Column(name="uid")
    var uid: String,

    @Column(name = "y_n")
    var yn: Boolean = false,

    @Column(name="created_at")
    var createdAt: LocalDateTime,

    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY, orphanRemoval = true)
    var replys: MutableList<Reply> = mutableListOf(),
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null


//    fun removeReply(reply: Reply) {
//        replys.remove(reply)
//    }

    fun updateTodos(todo: UpdateTodoRequest) {
        title = if(todo.title == "") title else todo.title
        content = if(todo.content == "") content else todo.content
        yn = todo.yn
    }
}
fun Todo.toResponse() : TodoResponse {
    return TodoResponse(
        id = id!!,
        title = title,
        content = content,
        uid = uid,
        yn = yn,
        createAt = createdAt
    )
}

fun Todo.toGetResponse() : GetTodoResponse {
    return GetTodoResponse(
        id = id!!,
        title = title,
        content = content,
        uid = uid,
        yn = yn,
        reply = replys.map { it.toResponse() }
    )
}
