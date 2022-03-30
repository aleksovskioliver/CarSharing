package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.domain.User
import com.sorsix.CarSharing.domain.Vehicle
import com.sorsix.CarSharing.repository.UserRepository
import com.sorsix.CarSharing.repository.VehicleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val vehicleRepository: VehicleRepository) {

    fun getDriver(id: Long) = userRepository.findById(id)

    fun createDriver(firstName: String,
                     lastName: String,
                     phoneNumber: String,
                     email: String,
                     password: String,
                     vehicleId: Long): User {
        val vehicle: Vehicle = vehicleRepository.findByIdOrNull(vehicleId) ?: throw Exception()
        return userRepository.save(User(id=0,firstName,lastName,phoneNumber,email,password))
    }




}