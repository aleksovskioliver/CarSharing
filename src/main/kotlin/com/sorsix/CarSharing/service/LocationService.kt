package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.api.request.CreateNewLocation
import com.sorsix.CarSharing.domain.Location
import com.sorsix.CarSharing.repository.LocationRepository
import org.springframework.stereotype.Service

@Service
class LocationService(private val locationRepository: LocationRepository) {

    fun create(newLocation: CreateNewLocation): Location{
        return locationRepository.save(Location(0,newLocation.address,newLocation.city,newLocation.country,newLocation.province))
    }
}