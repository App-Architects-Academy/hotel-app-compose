package me.darthwithap.hotel_app.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import me.darthwithap.hotel_app.domain.models.HotelDetails
import me.darthwithap.hotel_app.domain.repositories.HotelRepository
import me.darthwithap.hotel_app.utils.Result

class GetHotelDetailsUseCase(
  private val hotelRepository: HotelRepository
) {
  suspend operator fun invoke(limit: Int = 10): Flow<Result<List<HotelDetails>>> {
    return try {
      val hotelDetails = hotelRepository.getHotelDetails(limit)
      flowOf(Result.Success(hotelDetails))
    } catch (e: Exception) {
      flowOf(Result.Error(e))
    }
  }
}