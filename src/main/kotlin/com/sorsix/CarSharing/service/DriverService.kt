package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.domain.Reservation
import com.sorsix.CarSharing.domain.Vehicle
import com.sorsix.CarSharing.repository.DriverRepository
import com.sorsix.CarSharing.repository.VehicleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.ManyToMany
import javax.persistence.OneToOne

@Service
class DriverService(
    val driverRepository: DriverRepository,
    val vehicleRepository: VehicleRepository) {



    fun createDriver( firstName: String,
                      lastName: String,
                      phoneNumber: String,
                      email: String,
                      password: String,
                      vehicleId: Long){
        val vehicle: Vehicle = vehicleRepository.findByIdOrNull(vehicleId) ?: throw Exception()
    }

}