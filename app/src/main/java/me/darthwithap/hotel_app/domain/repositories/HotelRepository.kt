package me.darthwithap.hotel_app.domain.repositories

import me.darthwithap.hotel_app.domain.models.Hotel
import me.darthwithap.hotel_app.domain.models.HotelDetails

interface HotelRepository {
  suspend fun getHotels(limit: Int): List<Hotel>
  suspend fun getHotelDetails(limit: Int): List<HotelDetails>
}