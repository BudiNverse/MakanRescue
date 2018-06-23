package routes.post

import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import utils.deleteAuth
import utils.postAuth
import utils.putAuth

fun Route.comment() = route("/comment") {
    get("{id}") {
        TODO()
    }

    postAuth {
        TODO()
    }

    putAuth {
        TODO()
    }

    deleteAuth {
        TODO()
    }
}