package com.sorsix.CarSharing.api

import com.sorsix.CarSharing.api.authenticationDomain.AuthenticationRequest
import com.sorsix.CarSharing.api.authenticationDomain.AuthenticationResponse
import com.sorsix.CarSharing.service.MyUsersService
import com.sorsix.CarSharing.util.JwtUtil
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController(
    private val myUsersService: MyUsersService,
    private val jwtTokenUtil: JwtUtil,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping("/authenticate")
    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<Any> {
        try {
            this.authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(authenticationRequest.email, authenticationRequest.password)
            )
        } catch (exception: BadCredentialsException) {
            throw Exception("Incorrect username or password", exception)
        }

        val userDetails = this.myUsersService.loadUserByUsername(authenticationRequest.email)
        val jwt = this.jwtTokenUtil.generateToken(userDetails)
        return ResponseEntity.ok(AuthenticationResponse(jwt))
    }
}