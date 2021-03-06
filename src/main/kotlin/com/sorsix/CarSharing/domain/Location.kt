package com.sorsix.CarSharing.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Location(
    @Id
    val city: String,
    val lat: Double,
    val lng: Double,
    val country: String
)
