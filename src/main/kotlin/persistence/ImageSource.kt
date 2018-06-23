package persistence

import config.database.ImageT
import models.Image
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object ImageSource {

    fun getImageByPostId(postId: Int) = transaction {
        ImageT.select { ImageT.postId eq postId }.map(::Image)
    }


}