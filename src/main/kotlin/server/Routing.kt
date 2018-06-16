package server

import config.serverConfig
import io.ktor.application.ApplicationCallPipeline
import io.ktor.routing.Routing
import io.ktor.routing.route
import routes.User.auth
import utils.jwtAuth

fun Routing.setRouter() = route(serverConfig.apiPath) {
    /* All routing stuff to be placed here */
    intercept(ApplicationCallPipeline.Infrastructure) { jwtAuth() }
    /* All routes extensions goes here */
    auth()
}
