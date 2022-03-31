package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.api.request.CreateUserRequest
import com.sorsix.CarSharing.domain.Role
import com.sorsix.CarSharing.domain.User
import com.sorsix.CarSharing.repository.UserRepository
import com.sorsix.CarSharing.repository.VehicleRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val vehicleRepository: VehicleRepository,
    val passwordEncoder: PasswordEncoder
) {

    val logger: Logger = LoggerFactory.getLogger(UserService::class.java)

    fun getUser(id: Long) = userRepository.findById(id)

    fun createUser(newUser: CreateUserRequest): User {
        val role: Role = if (newUser.role == "ROLE_DRIVER") {
            Role.ROLE_DRIVER
        } else {
            Role.ROLE_CUSTOMER
        }

        val user = User(
            0,
            newUser.firstName,
            newUser.lastName,
            newUser.phoneNumber,
            newUser.email,
            passwordEncoder.encode(newUser.password),
            role
        )
        logger.info("[{}]", user)

        return userRepository.save(user)
    }
}