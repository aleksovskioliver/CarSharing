package com.sorsix.CarSharing.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Location(
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id: Long,
    val address: String,
    val city: String,
    val country: String,
    val province: String
)
