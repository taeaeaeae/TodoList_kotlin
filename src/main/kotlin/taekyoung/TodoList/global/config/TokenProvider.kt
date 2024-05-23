package taekyoung.TodoList.global.config

import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import taekyoung.TodoList.user.model.User
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*
import java.util.stream.Collectors
import javax.crypto.SecretKey

@Component
class TokenProvider (
    @Value("\${JWT_SECRET}")
    private var secret: String, // = null,

    @Value("\${JWT_ACCESS}")
    private var tokenValidityInMilliseconds: Long,

) : InitializingBean {
    private val logger: Logger = LoggerFactory.getLogger(TokenProvider::class.java)
    private val AUTHORITIES_KEY: String = "auth"
    private var key: SecretKey? = null

    override fun afterPropertiesSet() {
        val keyBytes: ByteArray =secret.toByteArray(StandardCharsets.UTF_8)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun createToken(authentication: Authentication): String {
//        tokenValidityInMilliseconds = tokenValidityInMilliseconds * 1000
        val authorities: String = authentication.getAuthorities().stream()
            .map { obj: GrantedAuthority -> obj.authority }
            .collect(Collectors.joining(","))

        // 토큰의 expire 시간을 설정
        val now: Long = Date().getTime()
        val validity: Date = Date(now + this.tokenValidityInMilliseconds)

        return Jwts.builder()
            .setSubject(authentication.getName())
            .claim(AUTHORITIES_KEY, authorities) // 정보 저장
            .signWith(key, SignatureAlgorithm.HS512) // 사용할 암호화 알고리즘과 , signature 에 들어갈 secret값 세팅
            .setExpiration(validity) // set Expire Time 해당 옵션 안넣으면 expire안함
            .compact()
    }

    // 토큰으로 클레임을 만들고 이를 이용해 유저 객체를 만들어서 최종적으로 authentication 객체를 리턴
    fun getAuthentication(token: String): Authentication {
//        TODO()
        val claims: Claims = Jwts
            .parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body

        val authorities: Collection<GrantedAuthority> = ArrayList()
//            Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                .map { role: String? -> SimpleGrantedAuthority(role) }
//                .collect(Collectors.toList())

        val principal: User = User(claims.getSubject(), "")

        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    // 토큰의 유효성 검증을 수행
    fun validateToken(token: String?): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return true
        } catch (e: SecurityException) {
            logger.info("잘못된 JWT 서명입니다.")
        } catch (e: MalformedJwtException) {
            logger.info("잘못된 JWT 서명입니다.")
        } catch (e: ExpiredJwtException) {
            logger.info("만료된 JWT 토큰입니다.")
        } catch (e: UnsupportedJwtException) {
            logger.info("지원되지 않는 JWT 토큰입니다.")
        } catch (e: IllegalArgumentException) {
            logger.info("JWT 토큰이 잘못되었습니다.")
        }
        return false
    }

}