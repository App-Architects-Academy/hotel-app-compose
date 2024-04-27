package me.darthwithap.hotel_app.domain.models

import java.time.LocalDate

data class Review(
  val author: Author,
  val text: String,
  val rating: Int,
  val votes: Int? = null,
  val response: String? = null,
  val created: LocalDate
) {
  data class Author(
    val name: String,
    val avatarUrl: String
  )
}