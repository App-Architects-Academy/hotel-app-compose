package me.darthwithap.hotel_app.presentation.hoteldetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.darthwithap.hotel_app.domain.usecases.GetHotelDetailsUseCase
import me.darthwithap.hotel_app.utils.Result
import kotlin.random.Random

class HotelDetailsScreenViewModel(
  private val getHotelDetails: GetHotelDetailsUseCase
) : ViewModel() {
  private val _state = MutableStateFlow<HotelDetailsUiState>(HotelDetailsUiState.Loading)
  val state = _state.asStateFlow()

  init {
    collectHotelDetails()
  }

  private fun collectHotelDetails() {
    viewModelScope.launch {
      getHotelDetails()
        .collect { result ->
          when (result) {
            is Result.Success -> {
              val hotelDetails = result.data.random()
              _state.value = HotelDetailsUiState.Success(hotelDetails, Random.nextBoolean())
            }

            is Result.Error -> _state.value = HotelDetailsUiState.Error
          }
        }
    }
  }
}