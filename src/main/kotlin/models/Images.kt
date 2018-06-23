package models

data class Images(val id: Int = 0,
                  val userId: Int,
                  val postId: Int,
                  val createdAt: Long,
                  val updatedAt: Long)