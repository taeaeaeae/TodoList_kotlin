package taekyoung.TodoList.global.config

import org.springframework.boot.autoconfigure.security.reactive.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
class SecurityConfig(
    private val tokenProvider : TokenProvider,
    private val jwtAuthenticationEntryPoint :  JwtAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler : JwtAccessDeniedHandler

) {
    // PasswordEncoder는 BCryptPasswordEncoder를 사용
    @Bean
    fun  passwordEncoder() : PasswordEncoder {
//        val bcrpt : BCryptPasswordEncoder
        return BCryptPasswordEncoder()
    }

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity.csrf { it.disable() }
            .exceptionHandling { it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler) }
            .headers { it.frameOptions { it.sameOrigin() } }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { it
                .requestMatchers("/api/authenticate").permitAll()// 로그인 api
                .requestMatchers("/api/signup").permitAll() // 회원가입 api
//                    .requestMatchers(PathRequest.toH2Console()).permitAll()// h2-console, favicon.ico 요청 인증 무시
                .requestMatchers("/favicon.ico").permitAll()
                .anyRequest().authenticated() // 그 외 인증 없이 접근X
            }//.apply(JwtSecurityConfig(tokenProvider))
        .build()
    }

}