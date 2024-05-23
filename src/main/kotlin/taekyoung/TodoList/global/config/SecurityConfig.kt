package taekyoung.TodoList.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
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
        httpSecurity

            .csrf { it.disable() }
            .exceptionHandling { it.authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler) }
            .headers { it.frameOptions { it.sameOrigin() } }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { it
                .requestMatchers("/api/authenticate").permitAll()// 로그인 api
                .requestMatchers("/api/signup").permitAll() // 회원가입 api
                .requestMatchers("/favicon.ico").permitAll()
                .anyRequest().authenticated() // 그 외 인증 없이 접근X
            }
//        apply(JwtSecurityConfig(tokenProvider))  // JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig class 적용
            .addFilterBefore(JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter::class.java)
        return httpSecurity.build()
    }

}