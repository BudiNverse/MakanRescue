package utils

import config.serverConfig

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.pipeline.PipelineContext
import io.ktor.request.header
import io.ktor.response.respond
import models.User
import persistence.UserSource

data class UserJwtPair(val jwt: String,
                       val user: User.StrippedUser)

object JwtUtils {

    fun parse(token: String) = Jwts.parser()
            .setSigningKey(serverConfig.jwtSecret)
            .parseClaimsJws(token)
            .body
            .let { it["id"].toString() }

    fun makeToken(user: User): String = Jwts.builder()
            .setSubject("Authentication")
            .setIssuer(serverConfig.issuer)
            .claim("id", user.id)
            .signWith(SignatureAlgorithm.HS256, serverConfig.jwtSecret)
            .compact()

}

fun PipelineContext<Unit, ApplicationCall>.jwtAuth() {
    val token = call.request.header("Authorization")?.removePrefix("Bearer ") ?: return
    val userId = JwtUtils.parse(token).toIntOrNull()
    val user = userId?.let { UserSource.getUserById(it) }
    if (user != null) call.attributes.put(User.key, user)
}

suspend fun PipelineContext<*, ApplicationCall>.requireLogin(): User {
    val user = call.attributes[User.key]
    requireNotNull(user) { call.respond(HttpStatusCode.Unauthorized, "User not logged in!") }
    return user
}
