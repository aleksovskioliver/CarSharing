package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.domain.Role
import com.sorsix.CarSharing.domain.User
import com.sorsix.CarSharing.repository.UserRepository
import com.sorsix.CarSharing.repository.VehicleRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val vehicleRepository: VehicleRepository) {

    fun getUser(id: Long) = userRepository.findById(id)

    fun createUser(firstName: String,
                     lastName: String,
                     phoneNumber: String,
                     email: String,
                     password: String,
                     role: Role): User {
        return userRepository.save(User(id=0,firstName,lastName,phoneNumber,email,password,role))
    }
}