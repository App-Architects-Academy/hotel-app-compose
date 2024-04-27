package me.darthwithap.hotel_app.data.repositories

import me.darthwithap.hotel_app.domain.models.Hotel
import me.darthwithap.hotel_app.domain.models.HotelDetails

interface HotelService {
  suspend fun getAllHotels(): List<Hotel>
  suspend fun getAllHotelDetails(): List<HotelDetails>
}