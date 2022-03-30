package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.api.request.CreateReservationRequest
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
        return reservationRepository.save(Reservation(0,driver,startTime = startTime,endTime = endTime,
            pickupLocation=pickupLocation,dropoffLocation = dropoffLocation,availableSeats = newReservationRequest.availableSeats,
            status = ReservationStatus.ACTIVE,tripCost = newReservationRequest.tripCost))
    }

    fun addCustomer(
        customerId: Long,
        reservationId: Long
    ): Reservation {
        val customer: User = userRepository.findByIdOrNull(customerId) ?: throw Exception()
        return updateReservation(reservationId,customer)
    }

    fun updateReservation(
        reservationId: Long,
        customer: User
    ):Reservation {
        val reservation: Reservation = reservationRepository.findByIdOrNull(reservationId) ?: throw Exception()
        val customerList: MutableList<User> = reservation.customers ?: mutableListOf()
        customerList.add(customer)
        return reservationRepository.save(reservation).let { result ->
            Reservation(
                result.id,
                result.driver,
                customerList,
                result.startTime,
                result.endTime,
                result.pickupLocation,
                result.dropoffLocation,
                result.tripCost,
                result.status,
                result.availableSeats-1
            )
        }
    }

}