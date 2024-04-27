package me.darthwithap.hotel_app.data.api

import me.darthwithap.hotel_app.data.randomHotels
import me.darthwithap.hotel_app.data.repositories.HotelService
import me.darthwithap.hotel_app.domain.models.Hotel
import me.darthwithap.hotel_app.domain.models.HotelDetails
import kotlin.random.Random

class HotelServiceImpl : HotelService {

  private val dummyHotelDetails: List<HotelDetails> by lazy {
    randomHotels(100).shuffled(Random.Default)
  }
  private val dummyHotels: List<Hotel> by lazy {
    dummyHotelDetails.map { it.hotel }.shuffled(Random.Default)
  }

  override suspend fun getAllHotels(): List<Hotel> = dummyHotels

  override suspend fun getAllHotelDetails(): List<HotelDetails> = dummyHotelDetails
}