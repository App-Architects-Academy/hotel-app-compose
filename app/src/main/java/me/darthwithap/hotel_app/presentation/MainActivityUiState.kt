package me.darthwithap.hotel_app.presentation

import me.darthwithap.hotel_app.domain.values.ThemeSetting

data class MainActivityUiState(
  val isLoading: Boolean = true, // For future app-startup tasks
  val theme: ThemeSetting = ThemeSetting.SYSTEM,
  val isLoggedIn: Boolean = false // Not logged in by default
)
