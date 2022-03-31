package com.sorsix.CarSharing.api.request

data class addCustomerToReservation(
    val customerId: Long,
    val reservationId: Long
)