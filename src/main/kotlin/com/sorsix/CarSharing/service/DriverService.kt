package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.domain.Driver
import com.sorsix.CarSharing.domain.Vehicle
import com.sorsix.CarSharing.repository.DriverRepository
import com.sorsix.CarSharing.repository.VehicleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DriverService(
    val driverRepository: DriverRepository,
    val vehicleRepository: VehicleRepository) {

    fun getDriver(id: Long) = driverRepository.findById(id)

    fun createDriver(firstName: String,
                     lastName: String,
                     phoneNumber: String,
                     email: String,
                     password: String,
                     vehicleId: Long): Driver {
        val vehicle: Vehicle = vehicleRepository.findByIdOrNull(vehicleId) ?: throw Exception()
        return driverRepository.save(Driver(id=0,firstName,lastName,phoneNumber,email,password,vehicle))
    }




}