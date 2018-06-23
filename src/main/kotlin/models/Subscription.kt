package models

data class LocationSubscription(val locationId: Int,
                                val userId: Int,
                                val createdAt: Long)

data class PostSubscription(val locationId: Int,
                            val userId: Int,
                            val createdAt: Long)