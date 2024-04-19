package me.darthwithap.hotel_app.domain.models

import java.time.LocalDate

data class Hotel(
  val id: String,
  val thumbnailImage: String,
  val name: String,
  val officialRating: Double,
  val numberOfReviews: Int,
  val address: Address,
  val location: Location,
  val primaryContact: HotelPrimaryContact
) {
  data class Address(
    val country: String,
    val city: String,
    val addressLine1: String,
    val addressLine2: String
  )

  data class Location(
    val longitude: Double,
    val latitude: Double
  )

  data class HotelPrimaryContact(
    val user: User,
    val hostSinceDate: LocalDate,
    val rating: Double,
    val numberOfReviews: Int,
    val details: String?,
    val isVerified: Boolean = false
  )
}