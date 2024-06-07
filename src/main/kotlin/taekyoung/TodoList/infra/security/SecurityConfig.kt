package taekyoung.TodoList.infra.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import taekyoung.TodoList.global.config.JwtAuthenticationEntryPoint
import taekyoung.TodoList.infra.security.jwt.TokenProvider
import taekyoung.TodoList.global.config.taekyoung.TodoList.global.config.UserDetailService
import taekyoung.TodoList.infra.security.jwt.JwtFilter


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val jwtFilter: JwtFilter,
    private val authenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val accessDeniedHandler: AccessDeniedHandler,
    private val service: UserDetailService,
    private val passwordEncoder: PasswordEncoderConfig

) {

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/api/authenticate",
                    "/users/signup",
                    "/users/login",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                .anyRequest().authenticated() // 그 외 인증 없이 접근X
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it.authenticationEntryPoint(authenticationEntryPoint)
                it.accessDeniedHandler(accessDeniedHandler)
            }
        return httpSecurity.build()
    }

    @Bean
    fun authenticationManager() : AuthenticationManager {   //알아서해
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(service)
        authProvider.setPasswordEncoder(passwordEncoder.passwordEncoder())
        return ProviderManager(authProvider)
    }


}