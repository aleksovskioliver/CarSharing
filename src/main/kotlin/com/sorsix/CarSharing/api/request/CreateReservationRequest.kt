package com.sorsix.CarSharing.api.request

data class CreateReservationRequest(
    val driverId: Long,
    val startTime: String,
    val endTime: String,
    val pickupLocationId: Long,
    val dropoffLocationId: Long,
    val tripCost: Int,
    val availableSeats: Int
)