package models

import config.database.LocationT
import org.jetbrains.exposed.sql.ResultRow

class Location(val id: Int = 0,
               val name: String,
               val locationLat: Long,
               val locationLng: Long) {

    constructor(rr: ResultRow) : this(
            id = rr[LocationT.id],
            name = rr[LocationT.name],
            locationLat = rr[LocationT.locationLat],
            locationLng = rr[LocationT.locationLng]
    )
}