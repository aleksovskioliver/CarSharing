package com.sorsix.CarSharing.repository

import com.sorsix.CarSharing.domain.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservationRepository : JpaRepository<Reservation,Long>{
}