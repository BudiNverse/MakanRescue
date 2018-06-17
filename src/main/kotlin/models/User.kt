package models

import config.database._User
import io.ktor.http.Parameters
import io.ktor.util.AttributeKey
import org.jetbrains.exposed.sql.ResultRow
import org.mindrot.jbcrypt.BCrypt
import persistence.UserSource

data class User(val id: Int,
                val email: String,
                val hashedPassword: String) {

    val strippedUser
        get() = StrippedUser(id, email)

    inner class StrippedUser(val id: Int,
                             val email: String)

    class UserAuth {
        class Login(private val param: Parameters) : RequestValidator(maxScore = 2) {
            var user: User? = null

            override fun validateRequest(): ArrayList<RequestError> {
                val email = param["email"]
                val password = param["password"]

                if (email.isNullOrBlank()) errors.add(RequestError.AUTH_MISSING_EMAIL).run { maxScore-- }
                if (password.isNullOrBlank()) errors.add(RequestError.AUTH_MISSING_PASSWORD).run { maxScore-- }

                if (maxScore <= 0) return errors

                // even if above is very defensive,
                // we should let the compiler handle null cases
                // just in case :)
                this.user = email?.let { UserSource.getUserByEmail(it) }

                if (user == null) errors.add(RequestError.AUTH_INVALID_CREDENTIALS).run { maxScore-- }

                if (maxScore == 2) {
                    val correctPassword = BCrypt.checkpw(password, user?.hashedPassword)
                    if (!correctPassword) errors.add(RequestError.AUTH_INVALID_CREDENTIALS)
                }

                return errors
            }

        }

        class Register(private val param: Parameters) : RequestValidator(maxScore = 5) {
            override fun validateRequest(): ArrayList<RequestError> {
                val email = param["email"]
                val password = param["password"]
                val cfmPassword = param["password2"]

                if (email.isNullOrBlank()) errors.add(RequestError.AUTH_MISSING_EMAIL).run { maxScore-- }
                if (password.isNullOrBlank()) errors.add(RequestError.AUTH_MISSING_PASSWORD).run { maxScore-- }
                if (cfmPassword.isNullOrBlank()) errors.add(RequestError.AUTH_MISSING_PASSWORD).run { maxScore-- }
                if (password != cfmPassword) errors.add(RequestError.AUTH_PASSWORD_NOT_THE_SAME).run { maxScore-- }

                if (maxScore < 5) return errors

                val hashedPassword = BCrypt.hashpw(cfmPassword, BCrypt.gensalt())

                if (maxScore == 5) {
                    val id = email?.let { UserSource.insertUser(it, hashedPassword) }
                    if (id == null) errors.add(RequestError.AUTH_PASSWORD_NOT_THE_SAME).run { maxScore-- }
                }

                return errors
            }

        }
    }

    constructor(resultRow: ResultRow) : this(
            id = resultRow[_User.id],
            email = resultRow[_User.email],
            hashedPassword = resultRow[_User.hashedPassword]
    )

    companion object {
        val key = AttributeKey<User>("user")
    }
}