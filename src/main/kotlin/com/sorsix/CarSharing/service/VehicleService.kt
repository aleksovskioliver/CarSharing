package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.api.request.CreateVehicleRequest
import com.sorsix.CarSharing.domain.User
import com.sorsix.CarSharing.domain.Vehicle
import com.sorsix.CarSharing.domain.exception.VehicleNotFound
import com.sorsix.CarSharing.repository.UserRepository
import com.sorsix.CarSharing.repository.VehicleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class VehicleService(
    val userRepository: UserRepository,
    val vehicleRepository: VehicleRepository) {

    fun createVehicle(newVehicle: CreateVehicleRequest): Vehicle{
        val driver: User = userRepository.findByIdOrNull(newVehicle.driverId) ?: throw Exception()
        return vehicleRepository.save(Vehicle(0,newVehicle.model,newVehicle.make,newVehicle.seats,driver))
    }

    fun getVehicle(id: Long): Vehicle {
        return vehicleRepository.findByIdOrNull(id) ?: throw VehicleNotFound("Vehicle with id $id is not found")
    }

    fun getVehicleForUser(driver: User): Vehicle{
        return vehicleRepository.findByDriver(driver)

    }

    fun deleteVehicle(id: Long){
        val vehicle: Vehicle = vehicleRepository.findByIdOrNull(id) ?: throw VehicleNotFound("Vehicle with id $id is not found")
        vehicleRepository.delete(vehicle)
    }
}