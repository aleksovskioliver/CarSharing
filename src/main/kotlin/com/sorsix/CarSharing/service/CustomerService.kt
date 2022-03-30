package com.sorsix.CarSharing.service

import com.sorsix.CarSharing.domain.Customer
import com.sorsix.CarSharing.repository.CustomerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CustomerService(val customerRepository: CustomerRepository) {

    fun createCustomer(
        firstName: String,
        lastName: String,
        email: String,
        phoneNumber: String,
        password: String
    ): Customer {
        val customer = Customer(0, firstName, lastName, phoneNumber, email, password)
        return customerRepository.save(customer)
    }

    fun getCustomerById(id: Long): Customer? {
        return customerRepository.findByIdOrNull(id)
    }


}