package me.darthwithap.hotel_app.domain.models

import me.darthwithap.hotel_app.domain.values.Gender

data class User(
  val email: String?,
  val phone: String?,
  val firstName: String,
  val lastName: String? = null,
  val photoUrl: String? = null,
  val gender: Gender? = null,
) {
  val fullName: String
    get() = "$firstName ${lastName ?: ""}".trim()
}
