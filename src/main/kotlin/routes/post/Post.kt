package routes.post

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import models.Post
import persistence.PostSource
import utils.deleteAuth
import utils.postAuth
import utils.putAuth

fun Route.post() = route("/post") {
    get {
        val posts = PostSource.getPosts().map(Post::fullPost)
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