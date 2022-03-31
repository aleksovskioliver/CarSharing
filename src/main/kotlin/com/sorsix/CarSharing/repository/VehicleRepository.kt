package com.sorsix.CarSharing.repository

import com.sorsix.CarSharing.domain.User
import com.sorsix.CarSharing.domain.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository : JpaRepository<Vehicle, Long> {
    fun findByDriver(driver: User): Vehicle
}