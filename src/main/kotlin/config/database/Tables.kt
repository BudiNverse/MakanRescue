package config.database

import org.jetbrains.exposed.sql.Table

object _User : Table("user") {
    val id = integer("id").autoIncrement().primaryKey()
    val email = varchar("email", 255).uniqueIndex()
    val hashedPassword = varchar("hashedPassword", 60)
}

object _Post : Table("Post") {
    val id = integer("id").autoIncrement().primaryKey()
    val title = varchar("title", 255)
    val description = varchar("description", 255)
    val foodAvail = integer("foodAvail")
    val expiry = long("expiry")
    val userId = integer("userId") references _User.id
    val createdAt = long("createdAt").default(System.currentTimeMillis())
    val updatedAt = long("updatedAt").default(System.currentTimeMillis())
    val deletedAt = long("deletedAt").nullable()
}