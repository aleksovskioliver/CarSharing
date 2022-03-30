package com.sorsix.CarSharing.domain

import javax.persistence.*

@Entity
@Table(name = "drivers")
data class Driver(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    @OneToOne
    val vehicle: Vehicle,
    @ManyToMany
    val reservation: MutableList<Reservation>? = null
)
