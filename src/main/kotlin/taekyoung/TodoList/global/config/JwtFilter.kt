//package taekyoung.TodoList.global.config
//
//import org.slf4j.Logger
//import org.slf4j.LoggerFactory
//import jakarta.servlet.FilterChain
//import jakarta.servlet.ServletException
//import jakarta.servlet.ServletRequest
//import jakarta.servlet.ServletResponse
//import jakarta.servlet.http.HttpServletRequest
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.stereotype.Component
//import org.springframework.util.StringUtils
//import org.springframework.web.filter.GenericFilterBean
//
//@Component
//class JwtFilter(
//    private val tokenProvider: TokenProvider
//) : GenericFilterBean() {
//    // 실제 필터릴 로직
//    // 토큰의 인증정보를 SecurityContext에 저장하는 역할 수행
//    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
//        val httpServletRequest = servletRequest as HttpServletRequest
//        val jwt = resolveToken(httpServletRequest)
//        val requestURI = httpServletRequest.requestURI
//
//
//        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
//            val authentication: Authentication = tokenProvider.getAuthentication(jwt)
//            SecurityContextHolder.getContext().authentication = authentication
//            Companion.logger.debug(
//                "Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}",
//                authentication.getName(),
//                requestURI
//            )
//        } else {
//            Companion.logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI)
//        }
//
//
//        filterChain.doFilter(servletRequest, servletResponse)
//    }
//
//    // Request Header 에서 토큰 정보를 꺼내오기 위한 메소드
//    private fun resolveToken(request: HttpServletRequest): String {
//        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
//
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7)
//        }
//
//        throw IllegalArgumentException("Invalid Authorization header")
//
//    }
//
//    companion object {
//        private val logger: Logger = LoggerFactory.getLogger(JwtFilter::class.java)
//        const val AUTHORIZATION_HEADER: String = "Authorization"
//    }
//}