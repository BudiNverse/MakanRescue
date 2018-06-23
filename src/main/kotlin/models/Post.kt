package models

import io.ktor.http.Parameters

data class Post(val id: Int = 0,
                val locationId: Int = 0,
                val locationDetails: String,
                val expiry: Long,
                val foodAvail: Int,
                val
                description: String? = null,
                val userId: Int,
                val createdAt: Long,
                val updatedAt: Long) {

    class PostOperations {
        class CreatePost(private val param: Parameters) : RequestValidator(maxScore = 5) {
            override fun validateRequest(): ArrayList<RequestError> {
                if (maxScore < maxScore) return errors

                return errors
            }
        }

        class UpdatePost(private val param: Parameters) : RequestValidator(maxScore = 5) {
            override fun validateRequest(): ArrayList<RequestError> {
                if (maxScore < maxScore) return errors

                return errors
            }

        }
    }
}