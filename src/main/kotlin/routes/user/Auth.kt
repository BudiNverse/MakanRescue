package routes.user

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.routing.route
import models.User
import utils.JwtUtils
import utils.UserJwtPair
import utils.badRequest

fun Route.auth() = route("/auth") {

    post("/login") {
        val params = call.receiveParameters()
        val validator = User.UserAuth.Login(params)
        val listOfErrors = validator.validateRequest()

        if (listOfErrors.isNotEmpty()) call.badRequest(listOfErrors)

        val user = validator.user
        user?.let(JwtUtils::makeToken)?.run { call.respond(UserJwtPair(this, user.strippedUser)) }
    }

    post("/register") {
        val params = call.receiveParameters()
        val validator = User.UserAuth.Register(params)
        val listOfErrors = validator.validateRequest()

        if (listOfErrors.isNotEmpty()) call.badRequest(listOfErrors)
        call.respond(HttpStatusCode.Created,
                "Your account has been created")
    }

}