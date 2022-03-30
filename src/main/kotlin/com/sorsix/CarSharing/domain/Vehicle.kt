package com.sorsix.CarSharing.domain

import java.sql.Driver
import javax.persistence.*

@Entity
data class Vehicle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val model: String,
    val make: String,
    val seats: Int,
    @OneToOne
    val driver: User
)
