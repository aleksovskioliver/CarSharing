package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.domain.*
import com.sorsix.CarSharing.repository.UserRepository
import com.sorsix.CarSharing.repository.ReservationRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.persistence.*

@Service
class ReservationService(
    val reservationRepository: ReservationRepository,
    val userRepository: UserRepository
) {

    fun getReservations(): MutableList<Reservation> {
        return reservationRepository.findAll()
    }

    fun createReservation(
        driverId: Long,
        startTime: LocalDateTime,
        endTime: LocalDateTime,
        pickupLocation: Location,
        dropoffLocation: Location,
        tripCost: Int,
        availableSeats: Int
    ): Reservation {
        val driver: User = userRepository.findByIdOrNull(driverId) ?: throw Exception()
        val reservation: Reservation = Reservation(
            id = 0, driver, startTime = startTime,
            endTime = endTime, pickupLocation = pickupLocation, dropoffLocation = dropoffLocation,
            tripCost = tripCost, availableSeats = availableSeats, status = ReservationStatus.ACTIVE
        )
        return reservationRepository.save(reservation)
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