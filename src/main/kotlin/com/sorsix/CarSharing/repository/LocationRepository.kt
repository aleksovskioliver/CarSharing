package com.sorsix.CarSharing.repository

import com.sorsix.CarSharing.domain.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : JpaRepository<Location, String> {
    fun findByCity(city: String): Location
}