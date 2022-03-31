package com.sorsix.CarSharing.api.request

data class CreateNewLocation(
    val city: String,
    val lat: Double,
    val lng: Double,
    val country: String
)