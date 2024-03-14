package me.darthwithap.hotel_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import me.darthwithap.hotel_app.presentation.navigation.HotelAppNavigator
import me.darthwithap.hotel_app.presentation.navigation.Routes
import me.darthwithap.hotel_app.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

  private val viewModel: MainActivityViewModel by viewModels()
  private var state: MainActivityUiState by mutableStateOf(MainActivityUiState())
  private var startDestination: String? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val splashScreen = installSplashScreen()
    lifecycleScope.launch {
      lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.state.collect {
          state = it
          decideStartDestination(state)
        }
      }
    }
    splashScreen.setKeepOnScreenCondition { state.isLoading && startDestination != null }
    setContent {
      AppTheme {
        HotelAppNavigator(startDestination!!)
      }
    }
  }

  private fun decideStartDestination(uiState: MainActivityUiState) {
    return if (uiState.isLoggedIn) {
      startDestination = Routes.HomeScreen
    } else {
      startDestination = Routes.AuthScreen
    }
  }

}