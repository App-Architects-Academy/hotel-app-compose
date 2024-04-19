package me.darthwithap.hotel_app.data.repositories

import me.darthwithap.hotel_app.domain.models.Hotel
import me.darthwithap.hotel_app.domain.models.HotelDetails
import me.darthwithap.hotel_app.domain.repositories.HotelRepository

class HotelRepositoryImpl(
  private val hotelService: HotelService,
) : HotelRepository {
  override suspend fun getHotels(limit: Int): List<Hotel> {
    return hotelService.getAllHotels().take(limit)
  }

  override suspend fun getHotelDetails(limit: Int): List<HotelDetails> {
    return hotelService.getAllHotelDetails().take(limit)
  }
}