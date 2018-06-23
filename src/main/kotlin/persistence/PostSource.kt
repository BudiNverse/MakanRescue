package persistence

import config.database.PostT
import models.Post
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object PostSource {

    fun getPosts() = transaction {
        PostT.select {
            (PostT.expiry greater System.currentTimeMillis()) and
                    (PostT.foodAvail neq 3)
        }.map(::Post)
    }

    fun getPostById(postId: Int) = transaction {
        PostT.select {
            (PostT.expiry greater System.currentTimeMillis()) and
                    (PostT.foodAvail neq 3) and (PostT.id eq postId)
        }.map(::Post)
    }

}