package com.sorsix.CarSharing.api

import com.sorsix.CarSharing.api.request.CreateUserRequest
import com.sorsix.CarSharing.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(private val userService: UserService) {

    @PostMapping("/create")
    fun createUser(@RequestBody userRequest: CreateUserRequest){
        userService.createUser(userRequest)
    }
}