package com.sorsix.CarSharing.api.request

data class CreateVehicleRequest(
    val model: String,
    val make: String,
    val seats: Int,
    val driverId: Long
)