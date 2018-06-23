package models

data class Images(val id: Int = 0,
                  val userId: Int,
                  val postId: Int,
                  val fileName: String,
                  val createdAt: Long,
                  val updatedAt: Long)