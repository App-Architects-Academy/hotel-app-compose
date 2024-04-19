package me.darthwithap.hotel_app.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import me.darthwithap.hotel_app.domain.models.Hotel
import me.darthwithap.hotel_app.domain.repositories.HotelRepository
import me.darthwithap.hotel_app.utils.Result

class GetHotelsUseCase(
  private val hotelRepository: HotelRepository
) {
  suspend operator fun invoke(limit: Int = 10): Flow<Result<List<Hotel>>> {
    return try {
      val hotels = hotelRepository.getHotels(limit)
      flowOf(Result.Success(hotels))
    } catch (e: Exception) {
      flowOf(Result.Error(e))
    }
  }
}