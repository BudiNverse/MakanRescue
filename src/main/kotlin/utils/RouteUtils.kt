package utils

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.pipeline.PipelineContext
import io.ktor.response.respond
import io.ktor.routing.*
import models.User


suspend fun ApplicationCall.badRequest(data: Any) =
        this.respond(HttpStatusCode.BadRequest, data)

class UserAppCtx(val user: User, val appCtx: PipelineContext<Unit, ApplicationCall>) {
    val call
        get() = appCtx.call
}

fun Route.getAuth(block: UserAppCtx.() -> Unit) =
        get {
            UserAppCtx(requireLogin(), this).block()
        }

fun Route.getAuth(route: String, block: suspend UserAppCtx.() -> Unit) =
        get(route) {
            UserAppCtx(requireLogin(), this).block()
        }

fun Route.postAuth(block: UserAppCtx.() -> Unit) =
        post {
            UserAppCtx(requireLogin(), this).block()
        }

fun Route.postAuth(route: String, block: UserAppCtx.() -> Unit) =
        post(route) {
            UserAppCtx(requireLogin(), this).block()
        }

fun Route.putAuth(block: UserAppCtx.() -> Unit) =
        put {
            UserAppCtx(requireLogin(), this).block()
        }

fun Route.putAuth(route: String, block: UserAppCtx.() -> Unit) =
        put(route) {
            UserAppCtx(requireLogin(), this).block()
        }

fun Route.deleteAuth(block: UserAppCtx.() -> Unit) =
        delete {
            UserAppCtx(requireLogin(), this).block()
        }

fun Route.deleteAuth(route: String, block: UserAppCtx.() -> Unit) =
        delete(route) {
            UserAppCtx(requireLogin(), this).block()
        }
