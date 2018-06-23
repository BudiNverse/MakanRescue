import config.database.DatabaseConfig
import config.database.PostT
import config.database.UserT
import config.serverConfig
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction
import server.setRouter

fun main(args: Array<String>) {
    //startServer()
}

private fun startServer() =
        embeddedServer(Netty, port = serverConfig.port) {
            /* Create database tables */
            DatabaseConfig()
            transaction {
                create(UserT, PostT)
            }

            install(CORS) {
                anyHost()
                header(HttpHeaders.Authorization)
                method(HttpMethod.Put)
            }

            install(ContentNegotiation) { gson { setPrettyPrinting() } }
            install(StatusPages) {
                exception<IllegalStateException> {
                    if (it.message == "No instance for key AttributeKey: user")
                        call.respond(HttpStatusCode.Unauthorized, "Missing JWT")
                }
            }

            install(Routing) {
                setRouter()
            }

        }.start(true)