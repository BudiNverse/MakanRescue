package persistence

import config.database.UserT
import models.User
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction


object UserSource {

    fun getUserById(id: Int) = transaction {
        UserT.select { UserT.id eq id }
                .first()
                .let(::User)
    }

    fun getUserByEmail(email: String) = transaction {
        UserT.select { UserT.email eq email }
                .first()
                .let(::User)
    }

    fun insertUser(_email: String, _hashedPassword: String) = transaction {
        UserT.insert {
            it[email] = _email
            it[hashedPassword] = _hashedPassword
        } get UserT.id
    }

}