package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.domain.*
import com.sorsix.CarSharing.repository.UserRepository
import com.sorsix.CarSharing.repository.ReservationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ReservationService(
    val reservationRepository: ReservationRepository,
    val userRepository: UserRepository) {

    fun createReservation(
        driverId: Long,
        startTime: LocalDateTime,
        endTime: LocalDateTime,
        pickupLocation: Location,
        dropoffLocation: Location,
        tripCost: Int,
        availableSeats: Int
    ): Reservation{
        val user: User = userRepository.findByIdOrNull(driverId) ?: throw Exception()
        val reservation: Reservation = Reservation(id=0,user,startTime = startTime,
            endTime = endTime,pickupLocation = pickupLocation,dropoffLocation = dropoffLocation,
            tripCost = tripCost,availableSeats = availableSeats,status = ReservationStatus.ACTIVE)
        return reservationRepository.save(reservation)
    }
}