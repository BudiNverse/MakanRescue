package config

import java.io.FileInputStream
import java.util.*

class ServerConfig(configFilePath: String) {
    val port: Int
    val jwtSecret: String
    val databaseUrl: String
    val databaseDriver: String
    val issuer: String
    val databaseUser: String
    val databasePassword: String
    val apiPath: String

    init {
        val prop = Properties().apply {
            this.load(FileInputStream(configFilePath))
        }

        port = prop.getProperty("port").toInt()
        jwtSecret = prop.getProperty("jwtSecret")
        databaseUrl = prop.getProperty("databaseUrl")
        databaseDriver = prop.getProperty("databaseDriver")
        issuer = prop.getProperty("issuer")
        databaseUser = prop.getProperty("databaseUser")
        databasePassword = prop.getProperty("databasePassword")
        apiPath = prop.getProperty("apiPath")
    }

}

val serverConfig = ServerConfig("serverConfig.properties")