package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.domain.*
import com.sorsix.CarSharing.repository.DriverRepository
import com.sorsix.CarSharing.repository.ReservationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Service
class ReservationService(
    val reservationRepository: ReservationRepository,
    val driverRepository: DriverRepository) {

    fun createReservation(
        driverId: Long,
        startTime: LocalDateTime,
        endTime: LocalDateTime,
        pickupLocation: Location,
        dropoffLocation: Location,
        tripCost: Int,
        availableSeats: Int
    ): Reservation{
        val driver: Driver = driverRepository.findByIdOrNull(driverId) ?: throw Exception()
        val reservation: Reservation = Reservation(id=0,driver,startTime = startTime,
            endTime = endTime,pickupLocation = pickupLocation,dropoffLocation = dropoffLocation,
            tripCost = tripCost,availableSeats = availableSeats,status = ReservationStatus.ACTIVE)
        return reservationRepository.save(reservation)
    }
}