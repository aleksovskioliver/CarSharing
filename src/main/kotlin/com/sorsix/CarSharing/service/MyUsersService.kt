package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.domain.exception.UserNotFoundException
import com.sorsix.CarSharing.repository.UserRepository
import com.sorsix.CarSharing.security.MyUserDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class MyUsersService(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByEmail(username) ?: throw UserNotFoundException("User with username $username is not found")
        return MyUserDetails(user)
    }
}