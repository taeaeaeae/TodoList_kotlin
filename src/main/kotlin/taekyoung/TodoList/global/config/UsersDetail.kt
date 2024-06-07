package taekyoung.TodoList.global.config.taekyoung.TodoList.global.config

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import taekyoung.TodoList.domain.user.model.User

class UsersDetail(
    private val user : User
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val col : MutableCollection<GrantedAuthority> = ArrayList()
        return col
    }

    override fun getPassword(): String {
        println("???????${user.pw}!!!!!!!!")
        return user.pw
    }

    override fun getUsername(): String {
        return user.id
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}