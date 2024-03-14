package me.darthwithap.hotel_app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
  private val _state = MutableStateFlow(MainActivityUiState())
  val state = _state.asStateFlow()

  init {
    viewModelScope.launch {
      delay(500) // Simulate background tasks in splash screen
      _state.update {
        it.copy(
          isLoading = false,
          isLoggedIn = isUserLoggedIn()
        )
      }
    }
  }

  private suspend fun isUserLoggedIn(): Boolean {
    delay(100) // Checking user logged in
    return false
  }
}