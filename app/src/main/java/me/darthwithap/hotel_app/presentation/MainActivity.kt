package me.darthwithap.hotel_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.launch
import me.darthwithap.hotel_app.domain.values.ThemeSetting
import me.darthwithap.hotel_app.presentation.auth.auth.AuthScreen
import me.darthwithap.hotel_app.presentation.navigation.Routes
import me.darthwithap.hotel_app.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private var state: MainActivityUiState by mutableStateOf(MainActivityUiState())
    private var startDestination: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect {
                    state = it
                    startDestination(state)
                }
            }
        }
        splashScreen.setKeepOnScreenCondition { state.isLoading || startDestination == null }

        setContent {
            AppTheme(darkTheme = isDarkTheme(state)) {
                Navigator(screen = AuthScreen)
            }
        }
    }

    private fun startDestination(uiState: MainActivityUiState) {
        return if (uiState.isLoggedIn) {
            startDestination = Routes.HomeScreen
        } else {
            startDestination = Routes.AuthScreen
        }
    }

    @Composable
    private fun isDarkTheme(state: MainActivityUiState) = when (state.theme) {
        ThemeSetting.SYSTEM -> isSystemInDarkTheme()
        ThemeSetting.LIGHT -> false
        ThemeSetting.DARK -> true
    }
}