package com.sorsix.CarSharing.api.request

data class CreateReservationRequest(
    val driverId: Long,
    val startTime: String,
    val endTime: String,
    val pickupLocation: String,
    val dropoutLocation: String,
    val tripCost: Int,
    val availableSeats: Int
)