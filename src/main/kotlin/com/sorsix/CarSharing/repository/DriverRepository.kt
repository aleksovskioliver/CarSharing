package com.sorsix.CarSharing.repository

import com.sorsix.CarSharing.domain.Driver
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DriverRepository : JpaRepository<Driver, Long>{
}