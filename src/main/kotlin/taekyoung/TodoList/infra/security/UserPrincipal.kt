package taekyoung.TodoList.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

data class UserPrincipal(
    val uid: String,
    val authorities: Collection<GrantedAuthority>
) {
    constructor(uid: String, roles: Set<String>) : this(
        uid,
        roles.map { SimpleGrantedAuthority("ROLE_$it") }
    )
}
