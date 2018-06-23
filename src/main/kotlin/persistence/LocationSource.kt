package persistence

import config.database.LocationT
import models.Location
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object LocationSource {

    fun getLocations() = transaction {
        LocationT.selectAll().map(::Location)
    }

    fun getLocationById(id: Int) = transaction {
        LocationT.select { LocationT.id eq id }
                .first()
                .let(::Location)
    }

}