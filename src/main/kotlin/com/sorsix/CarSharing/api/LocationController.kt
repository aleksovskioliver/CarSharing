package com.sorsix.CarSharing.api

import com.sorsix.CarSharing.api.request.CreateNewLocation
import com.sorsix.CarSharing.service.LocationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/location")
class LocationController(private val locationService: LocationService) {

    @PostMapping("/create")
    fun create(@RequestBody newLocations: List<CreateNewLocation>){
        newLocations.map {
            newLocation -> locationService.create(newLocation)
        }
    }
}