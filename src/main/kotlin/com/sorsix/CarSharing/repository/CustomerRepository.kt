package com.sorsix.CarSharing.repository

import com.sorsix.CarSharing.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer,Long> {
}