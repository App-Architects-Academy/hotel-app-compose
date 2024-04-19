package me.darthwithap.hotel_app.domain.models

import java.time.LocalDate

data class Booking(
  val bookingId: String,
  val userId: String,
  val hotelId: String,
  val roomTypeId: String,
  val checkInDate: LocalDate,
  val checkOutDate: LocalDate,
  val numberOfGuests: Int,
  val pricePaid: Double,
  val status: BookingStatus
)

enum class BookingStatus {
  PENDING, CONFIRMED, CANCELLED
}

