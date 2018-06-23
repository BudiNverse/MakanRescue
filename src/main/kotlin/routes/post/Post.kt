package routes.post

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import models.Post
import persistence.PostSource
import utils.*

fun Route.post() = route("/post") {
    get {
        val posts = PostSource.getPosts().map(Post::fullPost)
        call.respond(posts)
    }

    get("/{id}") {
        val params = call.parameters
        val validator = Post.PostOperations.GetPostById(params)
        val errors = validator.validateRequest()

        if (errors.isNotEmpty()) call.badRequest(errors)

        call.respond(validator.post)

    }

    getAuth("/myPosts") {
        val posts = PostSource.getUserPost(user.id)
        call.respond(posts)
    }

    postAuth {
        TODO()
    }

    putAuth {
        TODO()
    }

    deleteAuth {

    }
}