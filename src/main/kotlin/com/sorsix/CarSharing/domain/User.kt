package com.sorsix.CarSharing.domain

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    @Enumerated(value = EnumType.STRING)
    val role: Role,
    @OneToMany
    val vehicle: List<Vehicle>?,
    @ManyToMany
    val reservation: List<Reservation>
)
