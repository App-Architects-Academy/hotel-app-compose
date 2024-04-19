package me.darthwithap.hotel_app.presentation.hoteldetails

import me.darthwithap.hotel_app.domain.models.HotelDetails

sealed interface HotelDetailsUiState {
  data object Loading : HotelDetailsUiState
  data object Error : HotelDetailsUiState
  data class Success(
    val hotelDetails: HotelDetails,
    val isWishlisted: Boolean,
    val isHotelBooked: Boolean = false
  ) : HotelDetailsUiState {
    fun updateIsHotelBooked(value: Boolean) = copy(isHotelBooked = value)
  }
}
