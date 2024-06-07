package taekyoung.TodoList.global.config.taekyoung.TodoList.global.config

import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.InternalAuthenticationServiceException
import taekyoung.TodoList.domain.user.model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import taekyoung.TodoList.domain.user.repository.UserRepository

@Service
class UserDetailService (
    private val repository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        var user : User

        try {
            user = repository.findByIdOrNull(id)!!
        } catch (e : InternalAuthenticationServiceException) {
            throw UsernameNotFoundException("User not found")
        }

        return UsersDetail(user)


    }


}