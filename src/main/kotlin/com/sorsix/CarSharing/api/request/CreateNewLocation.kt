package com.sorsix.CarSharing.api.request

data class CreateNewLocation(
    val address: String,
    val city: String,
    val country: String,
    val province: String
)