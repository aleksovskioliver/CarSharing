package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.api.request.CreateNewLocation
import com.sorsix.CarSharing.domain.Location
import com.sorsix.CarSharing.repository.LocationRepository
import org.springframework.stereotype.Service

@Service
class LocationService(private val locationRepository: LocationRepository) {

    fun getLocations(): List<Location>{
        return locationRepository.findAll()
    }

    fun getLocationByCity(city: String): Location{
        return locationRepository.findByCity(city)
    }

    fun create(newLocation: CreateNewLocation): Location{
        return locationRepository.save(Location(newLocation.city,newLocation.lat,newLocation.lng,newLocation.country))
    }

    fun deleteLocation(city: String){
        val city: Location = locationRepository.findByCity(city)
        locationRepository.delete(city)
    }
}