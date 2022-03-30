package com.sorsix.CarSharing.domain

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Reservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne
    val driver: User,
    @ManyToMany(mappedBy = "reservation")
    val customers: MutableList<User>? = null,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    @OneToOne
    val pickupLocation: Location,
    @OneToOne
    val dropoffLocation: Location,
    val tripCost: Int,
    @Enumerated(value = EnumType.STRING)
    val status: ReservationStatus,
    val availableSeats: Int
)
