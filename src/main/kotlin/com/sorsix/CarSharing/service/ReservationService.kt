package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.api.request.CreateReservationRequest
import com.sorsix.CarSharing.api.request.addCustomerToReservation
import com.sorsix.CarSharing.domain.*
import com.sorsix.CarSharing.repository.LocationRepository
import com.sorsix.CarSharing.repository.UserRepository
import com.sorsix.CarSharing.repository.ReservationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.*

@Service
class ReservationService(
    val reservationRepository: ReservationRepository,
    val userRepository: UserRepository,
    val locationRepository: LocationRepository
) {
    var formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm")

    fun getReservations(): MutableList<Reservation> {
        return reservationRepository.findAll()
    }

    fun createReservation(newReservationRequest: CreateReservationRequest): Reservation {
        val driver = userRepository.findByIdOrNull(newReservationRequest.driverId) ?: throw Exception()
        val startTime = LocalDateTime.parse(newReservationRequest.startTime,formatter)
        val endTime = LocalDateTime.parse(newReservationRequest.endTime,formatter)
        val pickupLocation = locationRepository.findByIdOrNull(newReservationRequest.pickupLocationId) ?: throw Exception()
        val dropoffLocation = locationRepository.findByIdOrNull(newReservationRequest.dropoffLocationId) ?: throw Exception()
        return reservationRepository.save(Reservation(0,driver,customers = mutableListOf<User>(),startTime = startTime,endTime = endTime,
            pickupLocation=pickupLocation,dropoffLocation = dropoffLocation,availableSeats = newReservationRequest.availableSeats,
            status = ReservationStatus.ACTIVE,tripCost = newReservationRequest.tripCost))
    }

    fun addCustomerToReservation(addNewCustomer: addCustomerToReservation): Reservation {
        val reservation = reservationRepository.findByIdOrNull(addNewCustomer.reservationId) ?: throw Exception()
        val customer = userRepository.findByIdOrNull(addNewCustomer.customerId) ?: throw Exception()
        val reservationList = reservation.customers
        reservationList.add(customer)
        return reservationRepository.save(Reservation(
            reservation.id,
            reservation.driver,
            reservationList,
            reservation.startTime,
            reservation.endTime,
            reservation.pickupLocation,
            reservation.dropoffLocation,
            reservation.tripCost,
            reservation.status,
            reservation.availableSeats
        ))
    }
}