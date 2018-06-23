package models

import io.ktor.http.Parameters

data class Comment(val id: Int = 0,
                   val postId: Int,
                   val userId: Int,
                   val commentStr: String,
                   val createdAt: Long) {
    class CommentOperation {
        class CreateComment(private val param: Parameters) : RequestValidator(maxScore = 2) {
            override fun validateRequest(): ArrayList<RequestError> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        class UpdateComment(private val param: Parameters) : RequestValidator(maxScore = 2) {
            override fun validateRequest(): ArrayList<RequestError> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

        class DeleteComment(private val param: Parameters) : RequestValidator(maxScore = 1) {
            override fun validateRequest(): ArrayList<RequestError> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }

    }
}
