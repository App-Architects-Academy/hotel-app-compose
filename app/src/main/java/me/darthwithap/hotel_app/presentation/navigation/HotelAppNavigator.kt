package me.darthwithap.hotel_app.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import me.darthwithap.hotel_app.presentation.auth.auth.AuthScreen
import me.darthwithap.hotel_app.presentation.auth.login.LoginScreen
import me.darthwithap.hotel_app.presentation.auth.register.RegisterScreen
import me.darthwithap.hotel_app.ui.theme.AppTheme

@Composable
fun HotelAppNavigator(
  startDestination: String,
  modifier: Modifier = Modifier
) {
  val navController = rememberNavController()
  Scaffold(
    modifier = modifier.background(AppTheme.surfaceColor),
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
      composable(
        Routes.RegisterScreen,
        arguments = listOf(
          navArgument("email") {
            defaultValue = ""
          },
          navArgument("pass") {
            defaultValue = ""
          }
        )
      ) { backStackEntry ->
        RegisterScreen(
          onNavigateBackClick = navController::navigateUp,
          onRegisterAndAcceptClick = { navController.navigate(Routes.HomeScreen) },
          onEmailValueChange = {},
          onPasswordValueChange = {},
          prefillEmail = backStackEntry.arguments?.getString("email", ""),
          prefillPass = backStackEntry.arguments?.getString("pass", ""),
        )
      }
      composable(Routes.LoginScreen) {
        LoginScreen(
          onNavigateBackClick = navController::navigateUp,
          onRegisterClick = { email, pass ->
            val route = Routes.RegisterScreen.replace("{email}", email).replace("{pass}", pass)
            navController.navigate(route)
          }
        )
      }
      composable(Routes.HomeScreen) {
        // Todo()
      }
    }
  }
}