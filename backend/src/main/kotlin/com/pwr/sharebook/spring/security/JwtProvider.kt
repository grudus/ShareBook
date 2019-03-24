package com.pwr.sharebook.spring.security

import io.jsonwebtoken.*
import io.jsonwebtoken.SignatureAlgorithm.HS512
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class JwtProvider(private val jwtSecret: String, private val jwtExpiration: Int?) {
    private val logger = LoggerFactory.getLogger(JwtProvider::class.java)

    fun generateJwtToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserDetails

        return Jwts.builder()
                .setSubject(userPrincipal.username)
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + jwtExpiration!! * 1000))
                .signWith(HS512, jwtSecret)
                .compact()
    }

    fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException  ) {
            logger.info("Invalid JWT signature -> Message: {} ", e.message)
        } catch (e: MalformedJwtException) {
            logger.info("Invalid JWT token -> Message: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.info("Expired JWT token -> Message: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.info("Unsupported JWT token -> Message: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.info("JWT claims string is empty -> Message: {}", e.message)
        }

        return false
    }

    internal fun getUserNameFromJwtToken(token: String): String {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .body.subject
    }
}
