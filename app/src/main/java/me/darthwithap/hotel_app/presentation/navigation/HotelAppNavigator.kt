package me.darthwithap.hotel_app.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.darthwithap.hotel_app.presentation.auth.auth.AuthScreen
import me.darthwithap.hotel_app.presentation.auth.register.RegisterScreen

@Composable
fun HotelAppNavigator(
  startDestination: String,
  modifier: Modifier = Modifier
) {
  val navController = rememberNavController()
  Scaffold(
    modifier = modifier,
    topBar = {}
  ) { scaffoldPadding ->
    NavHost(
      modifier = Modifier.padding(scaffoldPadding),
      navController = navController,
      startDestination = startDestination
    ) {
      composable(Routes.AuthScreen) {
        AuthScreen(onLoginClick = {
          navController.navigate(route = Routes.LoginScreen)
        }, onRegisterClick = {
          navController.navigate(route = Routes.RegisterScreen)
        })
      }
      composable(Routes.RegisterScreen) {
        RegisterScreen(
          onNavigateBackClick = navController::navigateUp,
          onRegisterAndAcceptClick = { },
          onEmailValueChange = {},
          onPasswordValueChange = {}
        )
      }
      composable(Routes.LoginScreen) {

      }
      composable(Routes.HomeScreen) {
        // Todo()
      }
    }
  }
}