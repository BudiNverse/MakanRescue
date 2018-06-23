package models

import config.database.ImageT
import org.jetbrains.exposed.sql.ResultRow

data class Image(val id: Int = 0,
                 val userId: Int,
                 val postId: Int,
                 val fileName: String,
                 val createdAt: Long,
                 val updatedAt: Long) {

    constructor(rr: ResultRow) : this(
            id = rr[ImageT.id],
            userId = rr[ImageT.userId],
            postId = rr[ImageT.postId],
            fileName = rr[ImageT.fileName],
            createdAt = rr[ImageT.createdAt],
            updatedAt = rr[ImageT.updatedAt]
    )


}