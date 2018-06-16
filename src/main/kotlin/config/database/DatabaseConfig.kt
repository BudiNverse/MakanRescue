package config.database

import config.serverConfig
import org.jetbrains.exposed.sql.Database

class DatabaseConfig {
    init {
        Database.connect(
                url = serverConfig.databaseUrl,
                driver = serverConfig.databaseDriver,
                user = serverConfig.databaseUser,
                password = serverConfig.databasePassword
        )
    }
}