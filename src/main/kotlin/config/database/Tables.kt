package config.database

import org.jetbrains.exposed.sql.Table


// Extension helper functions for regularly used stuff
fun Table.createdAt() = long("createdAt").default(System.currentTimeMillis())
fun Table.updatedAt() = long("updatedAt").default(System.currentTimeMillis())
fun Table.intId() = integer("id")
fun Table.intId(idName: String) = integer(idName)


object UserT : Table("User") {
    val id = intId().autoIncrement().primaryKey()
    val email = varchar("email", 255).uniqueIndex()
    val hashedPassword = varchar("hashedPassword", 60)
    val isVerified = bool("isVerified").default(false)
}

object PostT : Table("Post") {
    val id = intId().autoIncrement().primaryKey()
    val locationId = intId("locationId") references LocationT.id
    val locationDetails = varchar("locationDetails", 255)
    val expiry = long("expiry")
    val foodAvail = integer("foodAvail")
    val description = varchar("description", 255).nullable()
    val userId = integer("userId") references UserT.id
    val createdAt = createdAt()
    val updatedAt = updatedAt()
}

object LocationT : Table("Location") {
    val id = intId().autoIncrement().primaryKey()
    val name = varchar("name", 255)
    val locationLat = long("locationLat")
    val locationLng = long("locationLng")
}

object PostSubT : Table("PostSubscription") {
    val id = intId().autoIncrement().primaryKey()
    val userId = intId("userId") references UserT.id
    val postId = intId("postId") references PostT.id
    val createdAt = createdAt()
}

object LocationSubT : Table("Subscription") {
    val id = intId().autoIncrement().primaryKey()
    val locationId = intId("locationId") references LocationT.id
    val userId = intId("userId") references UserT.id
    val createdAt = createdAt()
}

object CommentT : Table("Comment") {
    val id = intId().autoIncrement().primaryKey()
    val postId = intId("postId") references PostT.id
    val userId = intId("userId") references UserT.id
    val commentStr = varchar("commentStr", 255)
    val createdAt = createdAt()
}

object ImageT : Table("Image") {
    val id = integer("id").autoIncrement().primaryKey()
    val userId = intId("userId") references UserT.id
    val postId = intId("postId") references PostT.id
    val fileName = varchar("fileName", 255)
    val createdAt = createdAt()
    val updatedAt = updatedAt()
}