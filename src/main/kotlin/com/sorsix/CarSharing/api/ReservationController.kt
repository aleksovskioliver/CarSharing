package com.sorsix.CarSharing.api

import com.sorsix.CarSharing.api.request.CreateReservationRequest
import com.sorsix.CarSharing.api.request.addCustomerToReservation
import com.sorsix.CarSharing.service.ReservationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/reservation")
class ReservationController(private val reservationService: ReservationService) {

    @PostMapping("/create")
    fun createReservation(@RequestBody newReservation: CreateReservationRequest){
        reservationService.createReservation(newReservation)
    }

    @PostMapping("/addCustomer")
    fun addCustomer(@RequestBody addNewCustomer: addCustomerToReservation){
        reservationService.addCustomerToReservation(addNewCustomer)
    }
}