package utils

import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.pipeline.PipelineContext
import io.ktor.response.respond
import io.ktor.routing.*
import models.User


suspend fun ApplicationCall.badRequest(data: Any) =
        this.respond(HttpStatusCode.BadRequest, data)

class CustomerAppCtx(val user: User, val appCtx: PipelineContext<Unit, ApplicationCall>)

fun Route.getAuth(block: CustomerAppCtx.() -> Unit) =
        get {
            CustomerAppCtx(requireLogin(), this).block()
        }

fun Route.getAuth(route: String, block: CustomerAppCtx.() -> Unit) =
        get(route) {
            CustomerAppCtx(requireLogin(), this).block()
        }

fun Route.postAuth(block: CustomerAppCtx.() -> Unit) =
        post {
            CustomerAppCtx(requireLogin(), this).block()
        }

fun Route.postAuth(route: String, block: CustomerAppCtx.() -> Unit) =
        post(route) {
            CustomerAppCtx(requireLogin(), this).block()
        }

fun Route.putAuth(block: CustomerAppCtx.() -> Unit) =
        put {
            CustomerAppCtx(requireLogin(), this).block()
        }

fun Route.putAuth(route: String, block: CustomerAppCtx.() -> Unit) =
        put(route) {
            CustomerAppCtx(requireLogin(), this).block()
        }

fun Route.deleteAuth(block: CustomerAppCtx.() -> Unit) =
        delete {
            CustomerAppCtx(requireLogin(), this).block()
        }

fun Route.deleteAuth(route: String, block: CustomerAppCtx.() -> Unit) =
        delete(route) {
            CustomerAppCtx(requireLogin(), this).block()
        }
