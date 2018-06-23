package models

import config.database.PostT
import io.ktor.http.Parameters
import org.jetbrains.exposed.sql.ResultRow
import persistence.ImageSource
import persistence.LocationSource

class Post(val id: Int = 0,
           val locationId: Int = 0,
           val locationDetails: String,
           val expiry: Long,
           val foodAvail: Int,
           val description: String? = null,
           val userId: Int,
           val createdAt: Long,
           val updatedAt: Long) {

    constructor(rr: ResultRow) : this(
            id = rr[PostT.id],
            locationId = rr[PostT.locationId],
            locationDetails = rr[PostT.locationDetails],
            expiry = rr[PostT.expiry],
            foodAvail = rr[PostT.foodAvail],
            description = rr[PostT.description],
            userId = rr[PostT.userId],
            createdAt = rr[PostT.createdAt],
            updatedAt = rr[PostT.updatedAt]
    )

    inner class FullPost(val id: Int,
                         val location: Location,
                         val locationDetails: String,
                         val images: List<Image>,
                         val expiry: Long,
                         val foodAvail: Int,
                         val description: String? = null,
                         val userId: Int,
                         val createdAt: Long,
                         val updatedAt: Long)

    val fullPost: FullPost
        get() = FullPost(id, location, locationDetails, images, expiry, foodAvail, description, userId, createdAt, updatedAt)

    private val images: List<Image>
        get() = ImageSource.getImageByPostId(id)

    private val location: Location
        get() = LocationSource.getLocationById(locationId)


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