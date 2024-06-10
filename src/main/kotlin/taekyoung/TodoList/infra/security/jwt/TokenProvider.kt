package taekyoung.TodoList.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class TokenProvider (
    @Value("\${auth.jwt.issuer}")
    private val issuer: String,
    @Value("\${auth.jwt.secret}")
    private var secret: String, // = null,
    @Value("\${auth.jwt.accessTokenExpirationHour}")
    private var tokenValidityInMilliseconds: Long,

) {

    fun validateToken(token: String?): Result<Jws<Claims>> {
        return kotlin.runCatching {     //*********************************
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
        }
    }

    fun generateAccessToken(subject: String, uid: String, role: String): String {
        return generateToken(subject,uid,role, Duration.ofHours(tokenValidityInMilliseconds))
    }

    private fun generateToken(subject: String, uid: String, role: String, expirationPeriod: Duration): String {
        val claims : Claims = Jwts.claims()
            .add(mapOf("role" to role, "uid" to uid))
            .build()

        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
        val now = Instant.now()

        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationPeriod)))
            .claims(claims)
            .signWith(key)
            .compact()
    }

}