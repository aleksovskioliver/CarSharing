package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.domain.User
import com.sorsix.CarSharing.domain.Vehicle
import com.sorsix.CarSharing.repository.VehicleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class VehicleService(
    val vehicleRepository: VehicleRepository) {

    fun createVehicle(
        model: String,
        make: String,
        seats: Int,
        driver: User
    ): Vehicle{
        return vehicleRepository.save(Vehicle(0,model,make,seats,driver))
    }

    fun getVehicle(id: Long): Vehicle {
        return vehicleRepository.findByIdOrNull(id) ?: throw Exception()
    }
}